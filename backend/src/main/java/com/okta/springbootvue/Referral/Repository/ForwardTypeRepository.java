package com.cpe.backend.Referral.repository;

import com.cpe.backend.Referral.entity.ForwardType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ForwardTypeRepository extends JpaRepository<ForwardType, Long> {
	ForwardType findById(long id);
}