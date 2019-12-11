package com.cpe.springboot.Query.Repository;

import com.cpe.springboot.Query.Entity.CongenitalDisease;
import com.cpe.springboot.Query.Entity.Duration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DurationRepository extends JpaRepository<Duration, Long> {
    Duration findById(long id);

	void save(CongenitalDisease c);

}