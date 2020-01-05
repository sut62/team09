package com.okta.springbootvue.Registerpatient.Repository;

import com.okta.springbootvue.Registerpatient.Entity.Registerpatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RegisterpatientRepository extends JpaRepository<Registerpatient, Long> {
    
	Registerpatient findById(long id);


}

