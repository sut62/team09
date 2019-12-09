package com.okta.springbootvue.Appointment.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name="DOCTOR")
public class Doctor {
	@Id
	@SequenceGenerator(name="DOCTOR_seq",sequenceName="DOCTOR_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCTOR_seq")
	@Column(name="DOCTOR_ID",unique = true, nullable = true)
    private @NonNull Long doctorId;
    
    private @NonNull String doctorName;

    public Long getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}