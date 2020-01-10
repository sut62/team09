package com.okta.springbootvue.Diagnose.repository;

import com.okta.springbootvue.Diagnose.entity.Diagnose;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {
    Diagnose findById(long id);
}