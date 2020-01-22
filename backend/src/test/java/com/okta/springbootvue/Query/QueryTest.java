package com.okta.springbootvue.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.Query.Entity.Query;
import com.okta.springbootvue.Query.Repository.QueryRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class QueryTest {

    private Validator validator;

    @Autowired
    QueryRepository queryRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5910519_testCreateQueryOK() {
        Query query = new Query();
        query.setFirstName("นายสมคิด");
        query.setLastName("ป่วยนอก");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);
		query = queryRepository.saveAndFlush(query);

		Optional<Query> result = queryRepository.findById(query.getQueryIdx());
        assertEquals(1L, result.get().getQueryIdx());
        assertEquals("นายสมคิด", result.get().getFirstName());
        assertEquals("ป่วยนอก", result.get().getLastName());
        assertEquals(123, result.get().getPressureDIA());
        assertEquals(123, result.get().getPressureSYS());
        assertEquals("ปวดหัว", result.get().getSymptom());
        assertEquals(37.5f, result.get().getTemperature());
    }

	@Test
    void B5910519_testQueryIdMustNotBeNull() {
        Query query = new Query();
        query.setFirstName("นายสมคิด");
        query.setLastName("ป่วยนอก");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(null);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

		Set<ConstraintViolation<Query>> result = validator.validate(query);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("queryid", v.getPropertyPath().toString());
    }
	
	
	@Test
    void B5910519_testTemperatureMustNotBeNull() {
        Query query = new Query();
        query.setFirstName("นายสมคิด");
        query.setLastName("ป่วยนอก");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(null);

		Set<ConstraintViolation<Query>> result = validator.validate(query);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("temperature", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testPressureSYSMustNotBeNull() {
        Query query = new Query();
        query.setFirstName("นายสมคิด");
        query.setLastName("ป่วยนอก");
        query.setPressureDIA(123);
        query.setPressureSYS(null);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

		Set<ConstraintViolation<Query>> result = validator.validate(query);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("pressureSYS", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testPressureDIAMustNotBeNull() {
        Query query = new Query();
        query.setFirstName("นายสมคิด");
        query.setLastName("ป่วยนอก");
        query.setPressureDIA(null);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

		Set<ConstraintViolation<Query>> result = validator.validate(query);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("pressureDIA", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testSymptomMustNotBeNull() {
        Query query = new Query();
        query.setFirstName("นายสมคิด");
        query.setLastName("ป่วยนอก");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom(null);
        query.setTemperature(37.5f);

		Set<ConstraintViolation<Query>> result = validator.validate(query);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testFirstNameMustNotBeNull() {
        Query query = new Query();
        query.setFirstName(null);
        query.setLastName("ป่วยนอก");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

		Set<ConstraintViolation<Query>> result = validator.validate(query);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("firstName", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testLastNameMustNotBeNull() {
        Query query = new Query();
        query.setFirstName("นายสมคิด");
        query.setLastName(null);
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

		Set<ConstraintViolation<Query>> result = validator.validate(query);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("lastName", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testFirstNameNotLessThan2() {
        Query query = new Query();
        query.setFirstName("ก");
        query.setLastName("ป่วยนอก");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

		Set<ConstraintViolation<Query>> result = validator.validate(query);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("firstName", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testFirstNameNotMoreThan20() {
        Query query = new Query();
        query.setFirstName("ศาสตรจารย์ นายแพทย์ พลเอก สมคิดสมศักดิ์");
        query.setLastName("ป่วยนอก");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

		Set<ConstraintViolation<Query>> result = validator.validate(query);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("firstName", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testLastNameNotLessThan2() {
        Query query = new Query();
        query.setFirstName("นายสมคิด");
        query.setLastName("ก");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

		Set<ConstraintViolation<Query>> result = validator.validate(query);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("lastName", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testLastNameNotMoreThan20() {
        Query query = new Query();
        query.setFirstName("สมคิด");
        query.setLastName("อภิมหาสกุลชาติผ่องพรรณวดีมหาเศรษฐี");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

		Set<ConstraintViolation<Query>> result = validator.validate(query);
		
		assertEquals(1, result.size());
		
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("size must be between 2 and 20", v.getMessage());
        assertEquals("lastName", v.getPropertyPath().toString());
    }

    @Test
    void B5910519_testFirstNameCannotEnterNumber() {
        Query query = new Query();
        query.setFirstName("สมคิด0123");
        query.setLastName("ป่วยนอก");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        assertEquals(1, result.size());

        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("firstName", v.getPropertyPath().toString());
    }

    @Test
    void B5910519_testFirstNameCanNotEnterSpecialCharactor() {
        Query query = new Query();
        query.setFirstName("สมคิด()$%^^&&*");
        query.setLastName("ป่วยนอก");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        assertEquals(1, result.size());

        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("firstName", v.getPropertyPath().toString());
    }

    @Test
    void B5910519_testLastNameCannotEnterNumber() {
        Query query = new Query();
        query.setFirstName("สมคิด");
        query.setLastName("ป่วยนอก123");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        assertEquals(1, result.size());

        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("lastName", v.getPropertyPath().toString());
    }

    @Test
    void B5910519_testLastNameCanNotEnterSpecialCharactor() {
        Query query = new Query();
        query.setFirstName("สมคิด");
        query.setLastName("ป่วยนอก()$%^^&&*");
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        assertEquals(1, result.size());

        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("lastName", v.getPropertyPath().toString());
    }
}