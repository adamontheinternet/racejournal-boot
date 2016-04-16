package com.racejournal.controller;

import com.racejournal.domain.RaceType;
import com.racejournal.service.RaceService;
import com.racejournal.domain.Race;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by alaplante on 4/2/16.
 */
@RestController
public class RaceController {
    private Log log = LogFactory.getLog(RaceController.class);

    @Autowired
    RaceService raceService;

    @RequestMapping("/races")
    public List<Race> races(@RequestParam(value="raceType", required=false) String raceTypeString) {
        log.info(String.format("Get races for raceType %s", raceTypeString));
        RaceType raceType = null;
        if(raceTypeString != null) {
            raceType = RaceType.valueOf(raceTypeString);
        }
        return raceService.fetchRaces(raceType);
    }
}
