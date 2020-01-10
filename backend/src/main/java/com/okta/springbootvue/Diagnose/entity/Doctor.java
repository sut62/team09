package com.okta.springbootvue.Diagnose.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="DOCTOR")
public class Doctor {

    @Id
    @SequenceGenerator(name="DOCTOR_SEQ",sequenceName="DOCTOR_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DOCTOR_SEQ")
    @Column(name="DOCTOR_ID",unique = true, nullable = true)
    private Long doctorId;
    private String name;
    // private String userName;
    // private String password;
    
    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public String getUserName() {
    //     return userName;
    // }

    // public void setUserName(String userName) {
    //     this.userName = userName;
    // }

    // public String getPassword() {
    //     return password;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }

	
}