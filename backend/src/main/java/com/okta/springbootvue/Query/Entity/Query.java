package com.cpe.springboot.Query.Entity;

import java.util.Date;

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

import com.cpe.springboot.Query.Entity.Query;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="QUERY")
public class Query {

    @Id
    @SequenceGenerator(name="query_seq",sequenceName="queryl_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="queryl_seq")
    @Column(name = "QUERY_ID", unique = true, nullable = true)
    private @NonNull Long id;
    
    private @NonNull Date forwardDate;


    @Column(name="NOTE")
    private @NonNull String note;



    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Duration.class)
    @JoinColumn(name = "Duration_ID", insertable = true)
    private Duration duration;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CongenitalDisease.class)
    @JoinColumn(name = "ForwardTo_ID", insertable = true)
    private CongenitalDisease congenitalDisease;

    @ManyToOne()
    @JsonBackReference
    // @JoinColumn(name = "RegisterPatient_ID")
    //  private RegisterPatient registerPatient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public CongenitalDisease getCongenitalDisease() {
        return congenitalDisease;
    }

    public void setCongenitalDisease(CongenitalDisease congenitalDisease) {
        this.congenitalDisease = congenitalDisease;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}