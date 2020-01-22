package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.Query.Entity.CongenitalDisease;
import com.okta.springbootvue.Query.Repository.CongenitalDiseaseRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CongenitalDiseaseTest {

    private Validator validator;

    @Autowired
    CongenitalDiseaseRepository congenitalDiseaseRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5910519_testCreateCongenitalDiseaseOK() {
        CongenitalDisease congenitalDisease = new CongenitalDisease();
        congenitalDisease.setCongenitalDisease("โรคหัวใจ");
        congenitalDisease.setCongenitalDiseaseId(1L);
		congenitalDisease = congenitalDiseaseRepository.saveAndFlush(congenitalDisease);

		Optional<CongenitalDisease> result = congenitalDiseaseRepository.findById(congenitalDisease.getCongenitalDiseaseId());
        assertEquals(1L, result.get().getCongenitalDiseaseId());
        assertEquals("โรคหัวใจ", result.get().getCongenitalDisease());
    }

	
	@Test
    void B5910519_testCongenitalDiseaseMustNotBeNull() {
        CongenitalDisease congenitalDisease = new CongenitalDisease();
		congenitalDisease.setCongenitalDiseaseId(1L);
		congenitalDisease.setCongenitalDisease(null);

		Set<ConstraintViolation<CongenitalDisease>> result = validator.validate(congenitalDisease);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<CongenitalDisease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("CongenitalDisease", v.getPropertyPath().toString());
    }
}