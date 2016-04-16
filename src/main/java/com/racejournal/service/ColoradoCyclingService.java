package com.racejournal.service;

import com.racejournal.domain.Race;
import com.racejournal.domain.RaceType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alaplante on 4/2/16.
 */
@Service
public class ColoradoCyclingService {
    private Log log = LogFactory.getLog(RaceService.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${racesfile}")
    private String racesFile;
    @Value("${racesurl}")
    private String racesUrl;


    /*
    TODO refactor to remove code redundancy
     */

    public List<Race> loadLocal() {
        log.info(String.format("Load local races from file %s", racesFile));
        List<Race> races = new ArrayList<Race>();

        Resource resource = resourceLoader.getResource(String.format("classpath:%s", racesFile));
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            races = readAndParse(bufferedReader);
        } catch(IOException e) {
            log.error("Error reading from stream", e);
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch(IOException e) {
                log.info("Ignore error closing stream", e);
            }
        }
        return races;
    }

    public List<Race> loadRemote() {
        log.info(String.format("Load remote races from url %s", racesUrl));
        List<Race> races = new ArrayList<Race>();

        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(racesUrl);
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            races = readAndParse(bufferedReader);
        } catch(IOException e) {
            log.error("Error reading from stream", e);
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
            } catch(IOException e) {
                log.info("Ignore error closing stream", e);
            }
        }
        return races;
    }

    private List<Race> readAndParse(BufferedReader reader) throws IOException {
        List<Race> races = new ArrayList<Race>();
        Race race = null;
        String line = null;
        while ((line = reader.readLine()) != null) {  // UID:56b11fa5c2ed2
            log.debug(String.format("Reading line %s", line));
            if(line.startsWith("BEGIN")) {
                race = new Race();
            }
            if(line.startsWith("UID")) {
                race.setId(line.split(":")[1]);
            }
            if(line.startsWith("DTSTART")) { // DTSTART:20160402T000000Z
                String date = line.split(":")[1];
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(4, 6));
                int day = Integer.parseInt(date.substring(6, 8));
                race.setDate(LocalDate.of(year, month, day));
            }
            if(line.startsWith("LOCATION")) { // LOCATION:Louisville  CO
                String location = line.split(":")[1];
                log.debug(String.format("Parse city and state from %s", location));
                race.setCity(location.split("  ")[0]);
                if(location.split("  ").length > 1) race.setState(location.split("  ")[1]);
            }
            if(line.startsWith("SUMMARY")) { // SUMMARY:Louisville Criterium
                race.setName(line.split(":")[1]);
                race.setRaceType(categorizeRaceType(race.getName()));
            }
            if(line.startsWith("END")) {
                races.add(race);
            }
        }
        return races;
    }

    // TODO case sensitive?
    private RaceType categorizeRaceType(String name) {
        if(name.contains("Hill") || name.contains("HC")) {
            return RaceType.HILL_CLIMB;
        } else if(name.contains("TT") || name.contains("Time")) {
            return RaceType.TIME_TRIAL;
        } else if(name.contains("Crit")) {
            return RaceType.CRITERIUM;
        } else if(name.contains("Road") || name.contains("RR") || name.contains("Circuit")) {
            return RaceType.ROAD_RACE;
        }  else if(name.contains("Track")) {
            return RaceType.TRACK;
        } else if(name.contains("Cyclocross") || name.contains("CX") || name.contains("Cross") || name.contains("Cyclo-cross")) {
            return RaceType.CYCLOCROSS;
        }else {
            return RaceType.OTHER;
        }
    }
}
