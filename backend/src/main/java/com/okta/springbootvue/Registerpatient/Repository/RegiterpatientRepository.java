package com.okta.springbootvue.Regiterpatient.Repository;

import java.util.Optional;

import com.okta.springbootvue.Regiterpatient.Entity.Regiterpatient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RegiterpatientRepository extends JpaRepository<Regiterpatient, Long> {
    Optional<Regiterpatient> findById(Long id);
   
}

