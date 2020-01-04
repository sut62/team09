package com.okta.springbootvue.Diagnose.repository;

import java.util.Collection;
import java.util.Optional;

import com.okta.springbootvue.Diagnose.entity.Query;
import com.okta.springbootvue.Diagnose.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface QueryRepository extends JpaRepository<Query, Long> {
    Query findById(long id);

	Collection<Query> findByMember(Optional<Member> member);
}