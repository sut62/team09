package com.okta.springbootvue.Diagnose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.Diagnose.entity.Diagnose;
import com.okta.springbootvue.Diagnose.repository.DiagnoseRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DiagnoseTest {

    private Validator validator;

    @Autowired
    DiagnoseRepository diagnoseRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5904778_testNoteOK() {
        Diagnose diagnose = new Diagnose();
        // diagnose.setDiagnoseId(1L);
        diagnose.setNote("ตกบันได");
		diagnose = diagnoseRepository.saveAndFlush(diagnose);

		Optional<Diagnose> result = diagnoseRepository.findById(diagnose.getDiagnoseId());
        // assertEquals(1L, result.get().getDiagnoseId());
        assertEquals("ตกบันได", result.get().getNote());
    }

	@Test
    void B5904778_testNoteMustNotBeNull() {
        Diagnose diagnose = new Diagnose();
        diagnose.setDiagnoseId(1L);
        diagnose.setNote(null);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        assertEquals(1, result.size());

        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }
	
    @Test
    void B5904778_testNoteLessThan5() {
        Diagnose diagnose = new Diagnose();
        diagnose.setDiagnoseId(1L);
        diagnose.setNote("ตก");

		Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }
    @Test
    void B5904778_testNoteMoreThan30() {
        Diagnose diagnose = new Diagnose();
        diagnose.setDiagnoseId(1L);
        diagnose.setNote("ตกบันไดดดดดดดดดดดดดดดดดดดดดดดดดด");

		Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }
    
}