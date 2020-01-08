package com.okta.springbootvue.Appointment.Repository;

import com.okta.springbootvue.Appointment.Entity.Clinic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource  
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    Clinic findById(long clinicId);
}