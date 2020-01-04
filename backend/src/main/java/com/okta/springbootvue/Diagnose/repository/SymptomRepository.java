package com.okta.springbootvue.Diagnose.repository;

import com.okta.springbootvue.Diagnose.entity.Symptom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface SymptomRepository extends JpaRepository<Symptom, Long> {
    Symptom findById(long id);
}