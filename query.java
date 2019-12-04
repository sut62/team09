package com.cpe.backend.Query.entity;

import lombok.*; 

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cpe.backend.Register.Entity.Member;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Data 
@Entity 
@NoArgsConstructor 
@Table(name="Query")  

public class Query {

    @Id 
    @SequenceGenerator(name="Query_seq",sequenceName="Query_seq")  
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Query_seq")  
    @Column(name = "QUERY_ID", unique = true, nullable = true) 
    private @NonNull Long queryId;  

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = RegisterPatient.class)
    @JoinColumn(name = "ReEGISTERPATIENT_ID", insertable = true)
    private RegisterPatient registerPatient;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Duration.class)  
    @JoinColumn(name = "DURATION_ID", insertable = true)
    private Duration duration;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CongenitalDisease.class)
    @JoinColumn(name = "CONGENITALDISEASE_ID", insertable = true)
    private CongenitalDisease congenitalDisease;

    public Long getQueryId() { 
        return queryId;
    }

    public void setQueryId(Long queryId) { 
        this.queryId = queryId;
    }

    public Room getRegisterPatient() { 
        return registerPatient;
    }

    public void setRegisterPatient(RegisterPatient registerPatient) { 
        this.registerPatient = registerPatient;
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
}
