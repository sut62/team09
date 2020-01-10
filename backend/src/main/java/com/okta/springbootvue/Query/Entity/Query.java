package com.okta.springbootvue.Query.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.okta.springbootvue.Query.Entity.Query;
import com.okta.springbootvue.Registerpatient.Entity.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "QUERY")
public class Query {

    @Id
    @SequenceGenerator(name = "query_seq", sequenceName = "queryl_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "queryl_seq")
    @Column(name = "QUERY_ID", unique = true, nullable = true)
    private @NonNull Long queryid;
    private @NonNull Float temperature;
    private @NonNull Integer pressureSYS;
    private @NonNull Integer pressureDIA;
    private @NonNull String symptom;

    private @NonNull String firstName;

    private @NonNull String lastName;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Registerpatient.class)
    @JoinColumn(name = "REGISTERPATIENT_ID", insertable = true)
    private Registerpatient registerpatient;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Duration.class)
    @JoinColumn(name = "DURATION_ID", insertable = true)
    private Duration duration;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CongenitalDisease.class)
    @JoinColumn(name = "CONGENITALDISEASE_ID", insertable = true)
    private CongenitalDisease congenitalDisease;

    public Duration getDuration() {
        return this.duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public CongenitalDisease getCongenitalDisease() {
        return this.congenitalDisease;
    }

    public void setCongenitalDisease(CongenitalDisease congenitalDisease) {
        this.congenitalDisease = congenitalDisease;
    }

    @ManyToOne()
    @JsonBackReference

    public Long getQueryIdx() {
        return queryid;
    }

    public void setQueryIdx(Long queryid) {
        this.queryid = queryid;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Integer getPressureSYS() {
        return pressureSYS;
    }

    public void setPressureSYS(Integer pressureSYS) {
        this.pressureSYS = pressureSYS;
    }

    public Integer getPressureDIA() {
        return pressureDIA;
    }

    public void setPressureDIA(Integer pressureDIA) {
        this.pressureDIA = pressureDIA;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Registerpatient getRegisterpatient() {
        return this.registerpatient;
    }

    public void setRegisterpatient(Registerpatient registerpatient) {
        this.registerpatient = registerpatient;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}