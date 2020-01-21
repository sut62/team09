package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.Referral.Entity.*;
import com.okta.springbootvue.Referral.Repository.*;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class DeliverTest {

    private Validator validator;

    @Autowired
    DeliverRepository deliverRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5903191_testCreateDeliverOK() {
        Deliver deliver = new Deliver();
        deliver.setDeliver("รับไว้รักษาต่อ");
        deliver.setDeliverId(1L);
		deliver = deliverRepository.saveAndFlush(deliver);

		Optional<Deliver> result = deliverRepository.findById(deliver.getDeliverId());
        assertEquals(1L, result.get().getDeliverId());
        assertEquals("รับไว้รักษาต่อ", result.get().getDeliver());
    }

	@Test
    void B5903191_testDeliverIdMustNotBeNull() {
        Deliver deliver = new Deliver();
		deliver.setDeliverId(null);
		deliver.setDeliver("รับไว้รักษาต่อ");

		Set<ConstraintViolation<Deliver>> result = validator.validate(deliver);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Deliver> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("deliverId", v.getPropertyPath().toString());
    }
	
	
	@Test
    void B5903191_testDeliverMustNotBeNull() {
        Deliver deliver = new Deliver();
		deliver.setDeliverId(1L);
		deliver.setDeliver(null);

		Set<ConstraintViolation<Deliver>> result = validator.validate(deliver);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Deliver> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("deliver", v.getPropertyPath().toString());
    }
}