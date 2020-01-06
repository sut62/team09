package com.okta.springbootvue.RegisterDeaths.Repository;

import com.okta.springbootvue.RegisterDeaths.Entity.CauseofDeath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CauseofDeathRepository extends JpaRepository<CauseofDeath, Long> {
    CauseofDeath findById(long causeofDeathId);
}