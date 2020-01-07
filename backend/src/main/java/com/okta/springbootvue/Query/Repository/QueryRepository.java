package com.okta.springbootvue.Query.Repository;

import com.okta.springbootvue.Query.Entity.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface QueryRepository extends JpaRepository<Query, Long> {
    Query findById(long id);

}