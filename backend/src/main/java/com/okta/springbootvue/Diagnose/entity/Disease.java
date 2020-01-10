package com.okta.springbootvue.Diagnose.entity;
import lombok.*;

import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
    private @NonNull Long diseaseId;

    private @NonNull String name;

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