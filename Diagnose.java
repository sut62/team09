package com.okta.backend.entity;
import javax.persistence.*;
//import com.okta.backend.Booking.entity.Booking;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="diagnose_seq") //เอามาสร้างเป็นค่าที่จะเก็บเป็นตัวแปรid
    @Column(name = "DIAGNOSE_ID", unique = true, nullable = true)
    private @NonNull Long DiagnoseId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Disease.class)
    @JoinColumn(name = "Disease_ID", insertable = true)
    private Disease disease;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Doctor.class)
    @JoinColumn(name = "Doctor_ID", insertable = true)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Query.class)
    @JoinColumn(name = "Query_ID", insertable = true)
    private Query query;

    public void setDiagnoseId(Long diagnoseId){
      DiagnoseId = diagnoseId;
    }

    public Long getDiagnoseId(){
      return DiagnoseId;
    }

    public void setDoctor(Long doctor){
      doctor = doctor;
    }

    public Doctor getDoctor(){
      return doctor;
    }

    public void setDisease(Long disease){
      disease = disease;
    }

    public Disease getDisease(){
      return disease;
    }

    public void setQuery(Long query){
      query = query;
    }

    public Query getQuery(){
      return query;
    }
}