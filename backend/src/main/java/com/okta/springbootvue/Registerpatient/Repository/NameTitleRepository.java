package com.okta.springbootvue.Registerpatient.Repository;

import com.okta.springbootvue.Registerpatient.Entity.NameTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource  
public interface NameTitleRepository extends JpaRepository<NameTitle, Long> {
    
	NameTitle findById(long id);
}