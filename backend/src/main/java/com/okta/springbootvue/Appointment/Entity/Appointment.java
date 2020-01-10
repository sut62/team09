package com.okta.springbootvue.Appointment.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.okta.springbootvue.Diagnose.entity.Diagnose;
import com.okta.springbootvue.Diagnose.entity.Doctor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name="APPOINTMENT")
public class Appointment {

	@Id
	@SequenceGenerator(name="APPOINTMENT_seq",sequenceName="APPOINTMENT_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPOINTMENT_seq")
	@Column(name="APPOINTMENT_ID",unique = true, nullable = true)
    private @NonNull Long appointmentId;
    private @NonNull String appointDate;
    private @NonNull String appointTime;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clinic.class)
    @JoinColumn(name = "CLINIC_ID", insertable = true)
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Demeanor.class)
    @JoinColumn(name = "DEMEANOR_ID", insertable = true)
    private Demeanor demeanor;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Reason.class)
    @JoinColumn(name = "REASON_ID", insertable = true)
    private Reason reason;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Diagnose.class)
    @JoinColumn(name = "DIAGNOSE_ID", insertable = true)
    private Diagnose diagnose;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Doctor.class)
    @JoinColumn(name = "DOCTOR_ID", insertable = true)
    private Doctor doctor;

    public Doctor getDoctor() {
        return this.doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Diagnose getDiagnose() {
        return this.diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
    public Clinic getClinic() {
        return clinic;
    }
    public void setDemeanor(Demeanor demeanor) {
        this.demeanor = demeanor;
    }

    public Demeanor getDemeanor() {
        return demeanor;
    }
    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }
  
    public String getAppointDate() {
        return appointDate;
    }
    public void setAppointDate(String appointDate) {
        this.appointDate = appointDate;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }
}