package com.okta.springbootvue.Diagnose.Repository;

import com.okta.springbootvue.Diagnose.Entity.Disease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DiseaseRepository extends JpaRepository<Disease, Long> {
	Disease findById(long id);
}