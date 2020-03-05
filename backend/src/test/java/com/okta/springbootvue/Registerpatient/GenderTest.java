package com.okta.springbootvue.Registerpatient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.Registerpatient.Entity.*;
import com.okta.springbootvue.Registerpatient.Repository.*;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class GenderTest {

    private Validator validator;

    @Autowired
    GenderRepository genderRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5900985_testGenderOK() {
        Gender gender = new Gender();
        gender.setGender("หญิง");
        
		gender = genderRepository.saveAndFlush(gender);

		Optional<Gender> result = genderRepository.findById(gender.getGenderId());
        assertEquals("หญิง", result.get().getGender());
    }

	@Test
    void B5900985_testGenderMustNotBeNull() {
        Gender gender = new Gender();
        gender.setGender(null);

		Set<ConstraintViolation<Gender>> result = validator.validate(gender);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Gender> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("gender", v.getPropertyPath().toString());
    }
}