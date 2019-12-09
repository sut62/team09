package com.okta.springbootvue.Appointment.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.Date;

import com.okta.springbootvue.Diagnose.Entity;

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
    
    private @NonNull Date appointDate;

    private @NonNull Date time;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clinic.class)
    @JoinColumn(name = "CLINIC_ID", insertable = true)
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Demeanor.class)
    @JoinColumn(name = "DEMEANOR_ID", insertable = true)
    private Demeanor demeanor;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Reason.class)
    @JoinColumn(name = "REASON_ID", insertable = true)
    private Reason reason;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Doctor.class)
    @JoinColumn(name = "DOCTOR_ID", insertable = true)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Diagnose.class)
    @JoinColumn(name = "DIAGNOSE_ID", insertable = true)
    private Diagnose diagnose;

    public Long getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getAppointDate() {
        return appointDate;
    }
    public void setAppointDate(Date appointDate) {
        this.appointDate = appointDate;
    }

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
}