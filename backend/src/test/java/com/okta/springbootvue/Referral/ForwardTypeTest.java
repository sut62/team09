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
public class ForwardTypeTest {

    private Validator validator;

    @Autowired
    ForwardTypeRepository forwardTypeRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5903191_testCreateForwardTypeOK() {
        ForwardType forwardType = new ForwardType();
        forwardType.setForwardType("ส่งในจังหวัด");
        forwardType.setForwardTypeId(1L);
		forwardType = forwardTypeRepository.saveAndFlush(forwardType);

		Optional<ForwardType> result = forwardTypeRepository.findById(forwardType.getForwardTypeId());
        assertEquals(1L, result.get().getForwardTypeId());
        assertEquals("ส่งในจังหวัด", result.get().getForwardType());
    }
	
	@Test
    void B5903191_testForwardTypeMustNotBeNull() {
        ForwardType ForwardType = new ForwardType();
		ForwardType.setForwardTypeId(1L);
		ForwardType.setForwardType(null);

		Set<ConstraintViolation<ForwardType>> result = validator.validate(ForwardType);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<ForwardType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("forwardType", v.getPropertyPath().toString());
    }
}