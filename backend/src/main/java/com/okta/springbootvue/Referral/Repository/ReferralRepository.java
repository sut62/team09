package com.cpe.backend.Referral.repository;

import com.cpe.backend.Referral.entity.Referral;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:8080")
public
interface ReferralRepository extends JpaRepository<Referral, Long> {
}