package com.okta.springbootvue.Referral;

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
public class ReferralTest {

    private Validator validator;

    @Autowired
    ReferralRepository referralRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5903191_testCreateReferralOK() {
        Referral referral = new Referral();
        referral.setNote("ต้องรีบดำเนินการรักษา");
        referral.setReferralId(1L);
        referral = referralRepository.saveAndFlush(referral);

        Optional<Referral> result = referralRepository.findById(referral.getReferralId());
        assertEquals(1L, result.get().getReferralId());
        assertEquals("ต้องรีบดำเนินการรักษา", result.get().getNote());
    }

    @Test
    void B5903191_testReferralIdMustNotBeNull() {
        Referral Referral = new Referral();
        Referral.setReferralId(null);
        Referral.setNote("ต้องรีบดำเนินการรักษา");

        Set<ConstraintViolation<Referral>> result = validator.validate(Referral);

        assertEquals(1, result.size());

        ConstraintViolation<Referral> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("referralId", v.getPropertyPath().toString());
    }

    @Test
    void B5903191_testReferralMustNotBeNull() {
        Referral referral = new Referral();
        referral.setReferralId(1L);
        referral.setNote(null);

        Set<ConstraintViolation<Referral>> result = validator.validate(referral);

        assertEquals(1, result.size());

        ConstraintViolation<Referral> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void B5903191_testNoteNotLessThan5() {
        Referral referral = new Referral();
        referral.setReferralId(1L);
        referral.setNote("note");

        Set<ConstraintViolation<Referral>> result = validator.validate(referral);

        assertEquals(1, result.size());

        ConstraintViolation<Referral> v = result.iterator().next();
        assertEquals("size must be between 5 and 50", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void B5903191_testNoteNotMoreThan50() {
        Referral referral = new Referral();
        referral.setReferralId(1L);
        referral.setNote(
                "The number of people infected with a new virus in China tripled over the weekend");

        Set<ConstraintViolation<Referral>> result = validator.validate(referral);

        assertEquals(1, result.size());

        ConstraintViolation<Referral> v = result.iterator().next();
        assertEquals("size must be between 5 and 50", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void B5903191_testNoteCanNotEnterSpecialCharactor() {
        Referral referral = new Referral();
        referral.setReferralId(1L);
        referral.setNote("ต้องรีบดำเนินการรักษา@@#$");

        Set<ConstraintViolation<Referral>> result = validator.validate(referral);

        assertEquals(1, result.size());

        ConstraintViolation<Referral> v = result.iterator().next();
        assertEquals("must match \"^[0-9a-zA-Zก-๙\\s]+$\"", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }
}