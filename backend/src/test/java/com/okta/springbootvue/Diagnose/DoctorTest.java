package com.okta.springbootvue.Diagnose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.Diagnose.entity.Doctor;
import com.okta.springbootvue.Diagnose.repository.DoctorRepository;
// import com.okta.springbootvue.Query.Entity.Duration;
// import com.okta.springbootvue.Query.Repository.DurationRepository;


import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DoctorTest {

    private Validator validator;

    @Autowired
    DoctorRepository doctorRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5904778_testDoctorOK() {
        Doctor doctor = new Doctor();
        doctor.setName("อดทน พากเพียร");
        doctor.setDoctorId(1L);
		doctor = doctorRepository.saveAndFlush(doctor);

		Optional<Doctor> result = doctorRepository.findById(doctor.getDoctorId());
        assertEquals(1L, result.get().getDoctorId());
        assertEquals("อดทน พากเพียร", result.get().getName());
    }

	
	@Test
    void B5904778_testDoctorMustNotBeNull() {
        Doctor doctor = new Doctor();
        doctor.setName(null);
        doctor.setDoctorId(1L);

		Set<ConstraintViolation<Doctor>> result = validator.validate(doctor);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Doctor> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
}