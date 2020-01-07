package com.cpe.springbootvue.Referral.Repository;

import com.cpe.springbootvue.Referral.Entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
  
@RepositoryRestResource  
public interface ReferralRepository extends JpaRepository<Referral, Long> {
    @Query("SELECT t from Referral t WHERE t.referralId = :referralId")
    Referral findReferralById(Long referralId);
    Referral findById(long id);
}