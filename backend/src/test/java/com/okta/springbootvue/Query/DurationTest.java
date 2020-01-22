package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.Query.Entity.Duration;
import com.okta.springbootvue.Query.Repository.DurationRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DurationTest {

    private Validator validator;

    @Autowired
    DurationRepository durationRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5910519_testCreateDurationOK() {
        Duration duration = new Duration();
        duration.setDuration("1 วัน");
        duration.setDurationId(1L);
		duration = durationRepository.saveAndFlush(duration);

		Optional<Duration> result = durationRepository.findById(duration.getDurationId());
        assertEquals(1L, result.get().getDurationId());
        assertEquals("1 วัน", result.get().getDuration());
    }

	
	@Test
    void B5910519_testDurationMustNotBeNull() {
        Duration duration = new Duration();
		duration.setDurationId(1L);
		duration.setDuration(null);

		Set<ConstraintViolation<Duration>> result = validator.validate(duration);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Duration> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Duration", v.getPropertyPath().toString());
    }
}