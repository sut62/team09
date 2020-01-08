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
@Table(name="CLINIC")
public class Clinic {
	@Id
	@SequenceGenerator(name="CLINIC_seq",sequenceName="CLINIC_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLINIC_seq")
	@Column(name="CLINIC_ID",unique = true, nullable = true)
    private @NonNull Long clinicId;
    
    private @NonNull String clinic;

    public Long getClinicId() {
        return clinicId;
    }
    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public String getClinic() {
        return clinic;
    }
    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
}