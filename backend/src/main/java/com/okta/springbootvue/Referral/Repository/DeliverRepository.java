package com.cpe.springbootvue.Referral.Repository;

import com.cpe.springbootvue.Referral.Entity.Deliver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface DeliverRepository extends JpaRepository<Deliver, Long> {
    Deliver findById(long id);
}