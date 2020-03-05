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
public class NameTitleTest {

    private Validator validator;

    @Autowired
    NameTitleRepository nameTitleRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5900985_testNameTitleOK() {
        NameTitle nametitle = new NameTitle();
        nametitle.setNametitle("นางสาว");

		nametitle = nameTitleRepository.saveAndFlush(nametitle);

		Optional<NameTitle> result = nameTitleRepository.findById(nametitle.getNameTitleId());
        assertEquals("นางสาว", result.get().getNametitle());
    }

	@Test
    void B5900985_testNametitleMustNotBeNull() {
        NameTitle nametitle = new NameTitle();
        nametitle.setNametitle(null);

		Set<ConstraintViolation<NameTitle>> result = validator.validate(nametitle);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<NameTitle> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("nametitle", v.getPropertyPath().toString());
    }
}