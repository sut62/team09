package com.okta.springbootvue.RegisterDeaths.Repository;

import com.okta.springbootvue.RegisterDeaths.Entity.Place;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findById(long id);
}