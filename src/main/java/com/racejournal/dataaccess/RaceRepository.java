package com.racejournal.dataaccess;

import com.racejournal.domain.Race;
import com.racejournal.domain.RaceType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by alaplante on 4/16/16.
 */
public interface RaceRepository extends CrudRepository<Race, Long> {
    List<Race> findAll();
    List<Race> findRaceByRaceType(RaceType raceType);
}
