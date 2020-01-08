package com.okta.springbootvue.Referral.Repository;

import com.okta.springbootvue.Referral.Entity.ForwardTo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ForwardToRepository extends JpaRepository<ForwardTo, Long> {
    ForwardTo findById(long id);
}