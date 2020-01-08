package com.okta.springbootvue.Appointment.Repository;

import com.okta.springbootvue.Appointment.Entity.Demeanor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource  
public interface DemeanorRepository extends JpaRepository<Demeanor, Long> {
    Demeanor findById(long demeanorId);
}