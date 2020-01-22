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
public class ForwardToTest {

    private Validator validator;

    @Autowired
    ForwardToRepository forwardToRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5903191_testCreateForwardToOK() {
        ForwardTo forwardTo = new ForwardTo();
        forwardTo.setForwardTo("โรงพยาบาลเทคโนโลยีสุรนารี");
        forwardTo.setForwardToId(1L);
		forwardTo = forwardToRepository.saveAndFlush(forwardTo);

		Optional<ForwardTo> result = forwardToRepository.findById(forwardTo.getForwardToId());
        assertEquals(1L, result.get().getForwardToId());
        assertEquals("โรงพยาบาลเทคโนโลยีสุรนารี", result.get().getForwardTo());
    }
	
	@Test
    void B5903191_testForwardToMustNotBeNull() {
        ForwardTo ForwardTo = new ForwardTo();
		ForwardTo.setForwardToId(1L);
		ForwardTo.setForwardTo(null);

		Set<ConstraintViolation<ForwardTo>> result = validator.validate(ForwardTo);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<ForwardTo> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("forwardTo", v.getPropertyPath().toString());
    }
}