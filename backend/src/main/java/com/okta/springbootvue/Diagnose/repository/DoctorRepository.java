package com.okta.springbootvue.Diagnose.Repository;

import com.okta.springbootvue.Diagnose.Entity.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Doctor findById(long id);
}