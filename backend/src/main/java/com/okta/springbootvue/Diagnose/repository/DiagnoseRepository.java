package com.okta.springbootvue.Diagnose.Repository;

import com.okta.springbootvue.Diagnose.Entity.Diagnose;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:8080")
public
interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {
}