package com.racejournal.service;

import com.racejournal.dataaccess.RaceRepository;
import com.racejournal.domain.Race;
import com.racejournal.domain.RaceType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alaplante on 4/2/16.
 */
@Service
public class RaceService {
    private Log log = LogFactory.getLog(RaceService.class);

    @Autowired
    private ColoradoCyclingService coloradoCyclingService;

    @Autowired
    private RaceRepository raceRepository;

    public List<Race> fetchRaces(RaceType raceType) {
        log.info(String.format("Fetch races by type %s", raceType));
        List<Race> races = raceRepository.findAll();
        if(races.isEmpty()) {
            log.info("No races cached - Bootstrap and load race data");
            races = coloradoCyclingService.loadLocal();
            raceRepository.save(races);
        }
        return raceType != null ? raceRepository.findRaceByRaceType(raceType) : raceRepository.findAll();
    }
}
