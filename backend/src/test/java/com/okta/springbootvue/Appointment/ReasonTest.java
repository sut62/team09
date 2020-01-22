package com.okta.springbootvue.Appointment;

import com.okta.springbootvue.Appointment.Entity.*;
import com.okta.springbootvue.Appointment.Repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ReasonTest {

    @Autowired
    private ReasonRepository reasonRepository;

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B5905188_testReasonSuccess() {
        Reason reason = new Reason();
        reason.setReason("ผ่าตัด");
        
        reason = reasonRepository.saveAndFlush(reason);

        Optional<Reason> found = reasonRepository.findById(reason.getReasonId());
        assertEquals("ผ่าตัด", found.get().getReason());
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B5905188_testReasonMustNotBeNull() {
        Reason reason = new Reason();
        reason.setReason(null);
        
        Set<ConstraintViolation<Reason>> result = validator.validate(reason);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Reason> found = result.iterator().next();
        assertEquals("must not be null", found.getMessage());
        assertEquals("reason", found.getPropertyPath().toString());
    }
}