package com.okta.springbootvue.RegisterDeaths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import com.okta.springbootvue.RegisterDeaths.Entity.*;
import com.okta.springbootvue.RegisterDeaths.Repository.*;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PlaceTest {

    private Validator validator;

    @Autowired
    PlaceRepository placeRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5905836_testPlaceOK() {
        Place place = new Place();
        place.setPlace("บ้าน");
        
		place = placeRepository.saveAndFlush(place);

		Optional<Place> result = placeRepository.findById(place.getPlaceId());
        assertEquals("บ้าน", result.get().getPlace());
    }

	 @Test
     void B5905836_testPlaceMustNotBeNull() {
         Place place = new Place();
          place.setPlace("บ้าน");

	 	Set<ConstraintViolation<Place>> result = validator.validate(place);
		
	 	assertEquals(1, result.size());
		
         ConstraintViolation<Place> v = result.iterator().next();
         assertEquals("must not be null", v.getMessage());
         assertEquals("placeId", v.getPropertyPath().toString());
     }
}