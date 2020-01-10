package com.okta.springbootvue.Diagnose.entity;
//import java.util.Date;

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
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="DIAGNOSE")
public class Diagnose {

    @Id
    @SequenceGenerator(name="diagnose_seq",sequenceName="diagnose_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="diagnose_seq")
    @Column(name = "DIAGNOSE_ID", unique = true, nullable = true)
    private @NonNull Long DiagnoseId;
    
    private String nameRegister;
    


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Disease.class)
    @JoinColumn(name = "Disease_ID", insertable = true)
    private Disease disease;
    private String nameDisease;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "Query_ID")
    private Query query;
    

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Doctor.class)
    @JoinColumn(name = "Doctor_ID", insertable = true)
    private Doctor doctor;
    private String nameDoctor;


    public Long getDiagnoseId() {
      return DiagnoseId;
    }
  
    public void setDiagnoseId(Long diagnoseId) {
      DiagnoseId = diagnoseId;
    }
  
    public Disease getDisease() {
      return disease;
    }
  
    public void setDisease(Disease disease) {
      this.disease = disease;
    }
  
    public Query getQuery() {
      return query;
    }
  
    public void setQuery(Query query) {
      this.query = query;
    }

    public Doctor getDoctor() {
      return doctor;
    }
  
    public void setDoctor(Doctor doctor) {
      this.doctor = doctor;
    }

    public void setNameDisease(String name){
      this.nameDisease = name;
    }

    public void setNameRegister(String name){
      this.nameRegister = name;
    }

    public void setNameDoctor(String name){
      this.nameDoctor = name ;
  }

  

 

}