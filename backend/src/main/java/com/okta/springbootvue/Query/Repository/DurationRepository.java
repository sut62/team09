package com.okta.springbootvue.Query.Repository;

import com.okta.springbootvue.Query.Entity.CongenitalDisease;
import com.okta.springbootvue.Query.Entity.Duration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DurationRepository extends JpaRepository<Duration, Long> {
    Duration findById(long id);

	void save(CongenitalDisease c);

}