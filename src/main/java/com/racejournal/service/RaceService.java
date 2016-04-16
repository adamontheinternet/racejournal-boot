package com.racejournal.service;

import com.racejournal.domain.Race;
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
    ColoradoCyclingService coloradoCyclingService;

    public List<Race> fetchRaces() {
        log.info("Fetch races");
        return coloradoCyclingService.loadLocal();
    }
}
