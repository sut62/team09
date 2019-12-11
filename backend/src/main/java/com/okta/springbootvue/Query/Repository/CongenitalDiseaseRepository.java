package com.cpe.springboot.Query.Repository;

import com.cpe.springboot.Query.Entity.CongenitalDisease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CongenitalDiseaseRepository extends JpaRepository<CongenitalDisease, Long> {
    CongenitalDisease findById(long id);

}