package com.okta.springbootvue.Appointment.Repository;

import com.okta.springbootvue.Appointment.Entity.Reason;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource  
public interface ReasonRepository extends JpaRepository<Reason, Long> {
    Reason findById(long reasonId);
}