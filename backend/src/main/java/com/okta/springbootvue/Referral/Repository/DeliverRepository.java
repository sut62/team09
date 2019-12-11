package com.cpe.backend.Referral.repository;

import com.cpe.backend.Referral.entity.Deliver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DeliverRepository extends JpaRepository<Deliver, Long> {
	Deliver findById(long id);
}