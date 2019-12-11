package com.cpe.backend.Referral.repository;

import com.cpe.backend.Referral.entity.ForwardTo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ForwardToRepository extends JpaRepository<ForwardTo, Long> {
	ForwardTo findById(long id);
}