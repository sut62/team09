package com.okta.springbootvue.Diagnose.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="DISEASE")
public class Disease {
    @Id
    @SequenceGenerator(name="DISEASE_SEQ",sequenceName="DISEASE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DISEASE_SEQ")
    @Column(name="DISEASE_ID",unique = true, nullable = true)
    @NotNull
    private Long diseaseId;
    @NotNull
    private String name;

    public Long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Long diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}