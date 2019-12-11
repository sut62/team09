package com.cpe.springboot.Query.Repository;

import com.cpe.springboot.Query.Entity.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface QueryRepository extends JpaRepository<Query, Long> {
    Query findById(long id);

}