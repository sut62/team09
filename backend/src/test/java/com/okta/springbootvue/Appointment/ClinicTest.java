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
public class ClinicTest {

    @Autowired
    private ClinicRepository clinicRepository;

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B5905188_testClinicSuccess() {
        Clinic clinic = new Clinic();
        clinic.setClinic("โรคกระดูก");
        
        clinic = clinicRepository.saveAndFlush(clinic);

        Optional<Clinic> found = clinicRepository.findById(clinic.getClinicId());
        assertEquals("โรคกระดูก", found.get().getClinic());
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B5905188_testClinicMustNotBeNull() {
        Clinic clinic = new Clinic();
        clinic.setClinic(null);
        
        Set<ConstraintViolation<Clinic>> result = validator.validate(clinic);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Clinic> found = result.iterator().next();
        assertEquals("must not be null", found.getMessage());
        assertEquals("clinic", found.getPropertyPath().toString());
    }
}