package com.okta.springbootvue.RegisterDeaths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.RegisterDeaths.Entity.CauseofDeath;
import com.okta.springbootvue.RegisterDeaths.Repository.*;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CauseofDeathTest {

    private Validator validator;

    @Autowired
    CauseofDeathRepository causeofDeathRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5905836_testCauseofDeathOK() {
        CauseofDeath causeofDeath = new CauseofDeath();
        causeofDeath.setCauseofDeath("ฆาตรกรรม");
        
		causeofDeath = causeofDeathRepository.saveAndFlush(causeofDeath);

		Optional<CauseofDeath> result = causeofDeathRepository.findById(causeofDeath.getCauseofDeathId());
        assertEquals("ฆาตรกรรม", result.get().getCauseofDeath());
    }

         @Test
         void B5905836_testCauseofDeathMustNotBeNull() {
             CauseofDeath causeofDeath = new CauseofDeath();
             causeofDeath.setCauseofDeath("ฆาตรกรรม");

            Set<ConstraintViolation<CauseofDeath>> result = validator.validate(causeofDeath);

            assertEquals(1, result.size());

             ConstraintViolation<CauseofDeath> v = result.iterator().next();
             assertEquals("must not be null", v.getMessage());
             assertEquals("causeofdeathId", v.getPropertyPath().toString());
         }
}