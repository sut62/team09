package com.okta.springbootvue.RegisterDeaths.Repository;

import com.okta.springbootvue.RegisterDeaths.Entity.RegisterDeaths;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RegisterDeathsRepository extends JpaRepository<RegisterDeaths, Long> {
    RegisterDeaths findById(long id);
}