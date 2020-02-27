package com.okta.springbootvue.Diagnose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.PropertyMapper.SourceOperator;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.okta.springbootvue.Diagnose.entity.Diagnose;
import com.okta.springbootvue.Diagnose.entity.Disease;
import com.okta.springbootvue.Diagnose.entity.Doctor;
import com.okta.springbootvue.Diagnose.repository.DiagnoseRepository;
import com.okta.springbootvue.Diagnose.repository.DoctorRepository;
import com.okta.springbootvue.Query.Entity.Query;
import com.okta.springbootvue.Query.Entity.CongenitalDisease;
import com.okta.springbootvue.Query.Entity.Duration;
import com.okta.springbootvue.Query.Repository.CongenitalDiseaseRepository;
import com.okta.springbootvue.Query.Repository.DurationRepository;
import com.okta.springbootvue.Query.Repository.QueryRepository;
import com.okta.springbootvue.Registerpatient.Entity.Gender;
import com.okta.springbootvue.Registerpatient.Entity.NameTitle;
import com.okta.springbootvue.Registerpatient.Entity.Province;
import com.okta.springbootvue.Registerpatient.Entity.Registerpatient;
import com.okta.springbootvue.Registerpatient.Repository.GenderRepository;
import com.okta.springbootvue.Registerpatient.Repository.NameTitleRepository;
import com.okta.springbootvue.Registerpatient.Repository.ProvinceRepository;
import com.okta.springbootvue.Registerpatient.Repository.RegisterpatientRepository;
import com.okta.springbootvue.Diagnose.repository.DiseaseRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DiagnoseTest {


    @Autowired
    private DiagnoseRepository diagnoseRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private QueryRepository queryRepository;
    @Autowired
    private DurationRepository durationRepository;
    @Autowired
    private CongenitalDiseaseRepository congenitalDiseaseRepository;
    @Autowired
    private RegisterpatientRepository registerpatientRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private NameTitleRepository nameTitleRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    private Validator validator;
    Diagnose diagnose = new Diagnose();
    Disease disease = new Disease();
    Doctor doctor = new Doctor();
    Query query = new Query();

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกกก");
        r.setLastName("ขขขขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);
        registerpatientRepository.saveAndFlush(r);

        Duration duration = new Duration();
        duration.setDuration("1 วัน");
        durationRepository.saveAndFlush(duration);

        CongenitalDisease congenitalDisease = new CongenitalDisease();
        congenitalDisease.setCongenitalDisease("1 วัน");
        congenitalDiseaseRepository.saveAndFlush(congenitalDisease);

        query.setRegisterpatient(r);
        query.setCongenitalDisease(congenitalDisease);
        query.setDuration(duration);
        // query.setRegisterpatient(registerpatient);
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);
        queryRepository.saveAndFlush(query);

        disease.setName("กรดไหลย้อน");
        diseaseRepository.saveAndFlush(disease);

        doctor.setName("นอนน้อย นอนนะ");
        doctorRepository.saveAndFlush(doctor);
    }

    // *****************************************************************
    @Test
    void B5904778_testCreateDiagnoseOK() {

        Diagnose diagnose = new Diagnose();
        diagnose.setQuery(query);
        diagnose.setNote("ปวดท้อง");
        diagnose.setDisease(disease);
        diagnose.setDoctor(doctor);

        diagnose = diagnoseRepository.saveAndFlush(diagnose);

        Optional<Diagnose> found = diagnoseRepository.findById(diagnose.getDiagnoseId());
        assertEquals(query, found.get().getQuery());
        assertEquals("ปวดท้อง", found.get().getNote());
        assertEquals(disease, found.get().getDisease());
        assertEquals(doctor, found.get().getDoctor());
    }

    // ************************** test not null **************************
    @Test
    void B5904778_testNoteMustNotBeNull() {

        Diagnose diagnose = new Diagnose();
        diagnose.setQuery(query);
        diagnose.setNote(null);
        diagnose.setDisease(disease);
        diagnose.setDoctor(doctor);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());

    }

    @Test
    void B5904778_testDiseaseMustNotBeNull() {

        Diagnose diagnose = new Diagnose();
        diagnose.setQuery(query);
        diagnose.setNote("ปวดท้อง");
        diagnose.setDisease(null);
        diagnose.setDoctor(doctor);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("disease", v.getPropertyPath().toString());

    }

    @Test
    void B5904778_testDoctorMustNotBeNull() {

        Diagnose diagnose = new Diagnose();
        diagnose.setQuery(query);
        diagnose.setNote("ปวดท้อง");
        diagnose.setDisease(disease);
        diagnose.setDoctor(null);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("doctor", v.getPropertyPath().toString());

    }

    @Test
    void B5904778_testQueryMustNotBeNull() {

        Diagnose diagnose = new Diagnose();
        diagnose.setQuery(null);
        diagnose.setNote("ปวดท้อง");
        diagnose.setDisease(disease);
        diagnose.setDoctor(doctor);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("query", v.getPropertyPath().toString());

    }

    // ************************** test not null **************************

    @Test
    void B5904778_testNoteLessThan5() {
        Diagnose diagnose = new Diagnose();
        diagnose.setQuery(query);
        diagnose.setNote("ปว");
        diagnose.setDisease(disease);
        diagnose.setDoctor(doctor);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);
       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Diagnose> v = result.iterator().next();
       assertEquals("size must be between 5 and 30", v.getMessage());
       assertEquals("note", v.getPropertyPath().toString());
    }
    
    @Test
    void B5904778_testNoteMoreThan30() {
        Diagnose diagnose = new Diagnose();
        diagnose.setQuery(query);
        diagnose.setNote("ปวดท้องงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงงง");
        diagnose.setDisease(disease);
        diagnose.setDoctor(doctor);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);
       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Diagnose> v = result.iterator().next();
       assertEquals("size must be between 5 and 30", v.getMessage());
       assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void B5904778_testNotePattern() {
        Diagnose diagnose = new Diagnose();
        diagnose.setQuery(query);
        diagnose.setNote("$ปวดท้อง");
        diagnose.setDisease(disease);
        diagnose.setDoctor(doctor);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);
       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       ConstraintViolation<Diagnose> v = result.iterator().next();
       assertEquals("must match \"^[0-9a-zA-Zก-๙\\s]+$\"", v.getMessage());
       assertEquals("note", v.getPropertyPath().toString());
    }
    

}