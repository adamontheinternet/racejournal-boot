package com.racejournal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Created by alaplante on 4/2/16.
 */
public class Race {
    String id;
    String name;
    LocalDate date;
    String city;
    String state;
    RaceType raceType;

    public Race() {}

    public Race(String id, String name, LocalDate date, String city, String state, RaceType raceType) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.city = city;
        this.state = state;
        this.raceType = raceType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy", timezone="MST")
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public void setRaceType(RaceType raceType) {
        this.raceType = raceType;
    }
}
