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
public class ProvinceTest {

    private Validator validator;

    @Autowired
    ProvinceRepository provinceRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5900985_testProvinceTitleOK() {
        Province province = new Province();
        province.setProvince("นครราชสีมา");

		province = provinceRepository.saveAndFlush(province);

		Optional<Province> result = provinceRepository.findById(province.getProvinceId());
        assertEquals("นครราชสีมา", result.get().getProvince());
    }

	@Test
    void B5900985_testProvinceMustNotBeNull() {
        Province province = new Province();
        province.setProvince(null);
        province.setProvince("นครราชสีมา");

		Set<ConstraintViolation<Province>> result = validator.validate(province);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Province> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("provinceId", v.getPropertyPath().toString());
    }
}