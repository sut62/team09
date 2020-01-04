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
@Table(name="SYMPTOM")
public class Symptom {
    @Id
    @SequenceGenerator(name="SYMPTOM_SEQ",sequenceName="SYMPTOM_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SYMPTOM_SEQ")
    @Column(name="SYMPTOM_ID",unique = true, nullable = true)
    private @NonNull Long symptomId;
    private @NonNull String addSymptom;

}