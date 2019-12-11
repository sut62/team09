package com.okta.springbootvue.Regiterpatient.Repository;

import com.okta.springbootvue.Regiterpatient.Entity.Province;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource  
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Province findById(long id);
}