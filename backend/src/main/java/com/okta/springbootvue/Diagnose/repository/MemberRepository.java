package com.okta.springbootvue.Diagnose.repository;

import com.okta.springbootvue.Diagnose.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface MemberRepository extends JpaRepository<Member, Long> {
	Member findById(long id);

	Member findByName(String name);
}