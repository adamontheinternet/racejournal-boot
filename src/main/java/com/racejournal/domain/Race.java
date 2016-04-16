package com.racejournal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by alaplante on 4/2/16.
 */
@Entity
public class Race {
    @Id
    @GeneratedValue
    Long id;
    String uid;
    String name;
    LocalDate date;
    String city;
    String state;
    RaceType raceType;

    public Race() {}

    public Race(Long id, String uid, String name, LocalDate date, String city, String state, RaceType raceType) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.date = date;
        this.city = city;
        this.state = state;
        this.raceType = raceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

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
