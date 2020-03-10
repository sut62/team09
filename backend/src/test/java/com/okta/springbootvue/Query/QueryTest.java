package com.okta.springbootvue.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.Query.Entity.*;
import com.okta.springbootvue.Query.Repository.*;
import com.okta.springbootvue.Registerpatient.Entity.*;
import com.okta.springbootvue.Registerpatient.Repository.*;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class QueryTest {

    private Validator validator;
    private Duration duration;
    private CongenitalDisease congenitalDisease;
    private Registerpatient registerPatient;

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    DurationRepository durationRepository;

    @Autowired 
    CongenitalDiseaseRepository congenitalDiseaseRepository;

    @Autowired
    RegisterpatientRepository registerpatientRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private NameTitleRepository nameTitleRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        duration = new Duration();
        duration.setDuration("1 สัปดาห์");
        duration.setDurationId(1L);
        duration = durationRepository.saveAndFlush(duration);

        congenitalDisease = new CongenitalDisease();
        congenitalDisease.setCongenitalDisease("ไม่มีโรคประจำตัว");
        congenitalDisease.setCongenitalDiseaseId(1L);
        congenitalDisease = congenitalDiseaseRepository.saveAndFlush(congenitalDisease);

        Gender gender = new Gender();
        gender.setGender("หญิง");
        genderRepository.saveAndFlush(gender);

        NameTitle nameTitle = new NameTitle();
        nameTitle.setNametitle("นางสาว");
        nameTitleRepository.saveAndFlush(nameTitle);

        Province province = new Province();
        province.setProvince("เชียงใหม่");
        provinceRepository.saveAndFlush(province);

        registerPatient = new Registerpatient();
        registerPatient.setAddressDetail("addressDetail");
        registerPatient.setAge(23);
        registerPatient.setFirstName("firstName");
        registerPatient.setHeight(120);
        registerPatient.setLastName("lastName");
        registerPatient.setMobilePhone("0812345678");
        registerPatient.setWeight(45);
        registerPatient.setGender(gender);
        registerPatient.setNameTitle(nameTitle);
        registerPatient.setProvince(province);
        registerPatient = registerpatientRepository.saveAndFlush(registerPatient);
    }

    @Test
    void B5910519_testCreateQueryOK() {
        Query query = new Query();
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setDuration(duration);
        query.setRegisterpatient(registerPatient);
        query.setCongenitalDisease(congenitalDisease);
        query.setTemperature(37.5f);
        query = queryRepository.saveAndFlush(query);

        Optional<Query> result = queryRepository.findById(query.getQueryIdx());
        assertEquals(1L, result.get().getQueryIdx());
        assertEquals(123, result.get().getPressureDIA());
        assertEquals(123, result.get().getPressureSYS());
        assertEquals("ปวดหัว", result.get().getSymptom());
        assertEquals(37.5f, result.get().getTemperature());
    }

    @Test
    void B5910519_testQueryIdMustNotBeNull() {
        Query query = new Query();
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(null);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);
        query.setDuration(duration);
        query.setRegisterpatient(registerPatient);
        query.setCongenitalDisease(congenitalDisease);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("queryid", v.getPropertyPath().toString());
    }
    
    
    @Test
    void B5910519_testTemperatureMustNotBeNull() {
        Query query = new Query();
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(null);
        query.setDuration(duration);
        query.setRegisterpatient(registerPatient);
        query.setCongenitalDisease(congenitalDisease);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("temperature", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testPressureSYSMustNotBeNull() {
        Query query = new Query();
        query.setPressureDIA(123);
        query.setPressureSYS(null);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);
        query.setDuration(duration);
        query.setRegisterpatient(registerPatient);
        query.setCongenitalDisease(congenitalDisease);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("pressureSYS", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testPressureDIAMustNotBeNull() {
        Query query = new Query();
        query.setPressureDIA(null);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);
        query.setDuration(duration);
        query.setRegisterpatient(registerPatient);
        query.setCongenitalDisease(congenitalDisease);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("pressureDIA", v.getPropertyPath().toString());
    }
    @Test
    void B5910519_testSymptomMustNotBeNull() {
        Query query = new Query();
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom(null);
        query.setTemperature(37.5f);
        query.setDuration(duration);
        query.setRegisterpatient(registerPatient);
        query.setCongenitalDisease(congenitalDisease);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }

    @Test
    void B5910519_testSymptomMustNotLessThan5() {
        Query query = new Query();
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("less");
        query.setTemperature(37.5f);
        query.setDuration(duration);
        query.setRegisterpatient(registerPatient);
        query.setCongenitalDisease(congenitalDisease);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("size must be between 5 and 40", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }

    @Test
    void B5910519_testSymptomMustNotMoreThan40() {
        Query query = new Query();
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("symtom string must not more than 40 charactor");
        query.setTemperature(37.5f);
        query.setDuration(duration);
        query.setRegisterpatient(registerPatient);
        query.setCongenitalDisease(congenitalDisease);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("size must be between 5 and 40", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }

    @Test
    void B5910519_testDurationMustNotBeNull() {
        Query query = new Query();
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("symtom");
        query.setTemperature(37.5f);
        query.setDuration(null);
        query.setRegisterpatient(registerPatient);
        query.setCongenitalDisease(congenitalDisease);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("duration", v.getPropertyPath().toString());
    }

    @Test
    void B5910519_testCongenitalDiseaseMustNotBeNull() {
        Query query = new Query();
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("symtom");
        query.setTemperature(37.5f);
        query.setDuration(duration);
        query.setRegisterpatient(registerPatient);
        query.setCongenitalDisease(null);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("congenitalDisease", v.getPropertyPath().toString());
    }

    @Test
    void B5910519_testRegisterPatientMustNotBeNull() {
        Query query = new Query();
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("symtom");
        query.setTemperature(37.5f);
        query.setDuration(duration);
        query.setRegisterpatient(null);
        query.setCongenitalDisease(congenitalDisease);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("registerpatient", v.getPropertyPath().toString());
    }
    

    @Test
    void B5910519_testSymptomMustNotEnterSpecialCharactor() {
        Query query = new Query();
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setQueryIdx(1L);
        query.setSymptom("less@$@#%");
        query.setTemperature(37.5f);
        query.setDuration(duration);
        query.setRegisterpatient(registerPatient);
        query.setCongenitalDisease(congenitalDisease);

        Set<ConstraintViolation<Query>> result = validator.validate(query);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Query> v = result.iterator().next();
        assertEquals("must match \"^[0-9A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("symptom", v.getPropertyPath().toString());
    }
}