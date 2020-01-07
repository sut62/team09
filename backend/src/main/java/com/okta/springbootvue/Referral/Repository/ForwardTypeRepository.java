package com.cpe.springbootvue.Referral.Repository;

import com.cpe.springbootvue.Referral.Entity.ForwardType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface ForwardTypeRepository extends JpaRepository<ForwardType, Long> {
    ForwardType findById(long id);
}