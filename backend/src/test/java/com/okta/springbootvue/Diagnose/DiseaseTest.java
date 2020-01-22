package com.okta.springbootvue.Diagnose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.Diagnose.entity.Disease;
import com.okta.springbootvue.Diagnose.repository.DiseaseRepository;
// import com.okta.springbootvue.Query.Entity.Duration;
// import com.okta.springbootvue.Query.Repository.DurationRepository;


import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DiseaseTest {

    private Validator validator;

    @Autowired
    DiseaseRepository diseaseRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5904778_testDiseaseOK() {
        Disease disease = new Disease();
        disease.setName("กรดไหลย้อน");
        disease.setDiseaseId(1L);
		disease = diseaseRepository.saveAndFlush(disease);

		Optional<Disease> result = diseaseRepository.findById(disease.getDiseaseId());
        assertEquals(1L, result.get().getDiseaseId());
        assertEquals("กรดไหลย้อน", result.get().getName());
    }

	
	@Test
    void B5904778_testDiseaseMustNotBeNull() {
        Disease disease = new Disease();
        disease.setName(null);
        disease.setDiseaseId(1L);

		Set<ConstraintViolation<Disease>> result = validator.validate(disease);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Disease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
}