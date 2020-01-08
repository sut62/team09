package com.okta.springbootvue.Appointment.Repository;

import com.okta.springbootvue.Appointment.Entity.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource  
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findById(long appointmentId);
}