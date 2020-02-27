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
import com.okta.springbootvue.Diagnose.repository.*;
import com.okta.springbootvue.Query.Entity.*;
import com.okta.springbootvue.Query.Repository.*;
import com.okta.springbootvue.Diagnose.entity.*;
import com.okta.springbootvue.Referral.Entity.*;
import com.okta.springbootvue.Referral.Repository.*;
import com.okta.springbootvue.Registerpatient.Entity.*;
import com.okta.springbootvue.Registerpatient.Repository.*;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ReferralTest {

    private Validator validator;
    private Deliver deliver;
    private ForwardTo forwardTo;
    private ForwardType forwardType;
    private Diagnose diagnose;
    private Disease disease;
    private Doctor doctor;
    private Query query;
    private Duration duration;
    private CongenitalDisease congenitalDisease;
    private Registerpatient registerpatient;

    @Autowired
    ReferralRepository referralRepository;

    @Autowired
    ForwardToRepository forwardToRepository;

    @Autowired
    ForwardTypeRepository forwardTypeRepository;

    @Autowired
    DeliverRepository deliverRepository;

    @Autowired
    DiagnoseRepository diagnoseRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    NameTitleRepository nameTitleRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    RegisterpatientRepository registerpatientRepository;

    @Autowired 
    DurationRepository durationRepository;

    @Autowired
    CongenitalDiseaseRepository congenitalDiseaseRepository;

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    DiseaseRepository diseaseRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        registerpatient = new Registerpatient();
        registerpatient.setFirstName("กกกกกก");
        registerpatient.setLastName("ขขขขขข");
        registerpatient.setAge(33);
        registerpatient.setWeight(70);
        registerpatient.setHeight(170);
        registerpatient.setAddressDetail("Address");
        registerpatient.setMobilePhone("0819658742");
        registerpatient.setGender(gender);
        registerpatient.setNameTitle(nameTitle);
        registerpatient.setProvince(province);
        registerpatient = registerpatientRepository.saveAndFlush(registerpatient);

        duration = new Duration();
        duration.setDuration("1 วัน");
        duration = durationRepository.saveAndFlush(duration);

        congenitalDisease = new CongenitalDisease();
        congenitalDisease.setCongenitalDisease("1 วัน");
        congenitalDisease = congenitalDiseaseRepository.saveAndFlush(congenitalDisease);

        query = new Query();
        query.setRegisterpatient(registerpatient);
        query.setCongenitalDisease(congenitalDisease);
        query.setDuration(duration);
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setSymptom("ปวดหัว");
        query.setTemperature(37.5f);
        query = queryRepository.saveAndFlush(query);

        disease = new Disease();
        disease.setName("กรดไหลย้อน");
        disease = diseaseRepository.saveAndFlush(disease);

        doctor = new Doctor();
        doctor.setName("นอนน้อย นอนนะ");
        doctor = doctorRepository.saveAndFlush(doctor);

        forwardTo = new ForwardTo();
        forwardTo.setForwardTo("forwardTo");
        forwardTo.setForwardToId(1L);
        forwardTo = forwardToRepository.saveAndFlush(forwardTo);

        forwardType = new ForwardType();
        forwardType.setForwardType("forwardType");
        forwardType.setForwardTypeId(1L);
        forwardType = forwardTypeRepository.saveAndFlush(forwardType);

        deliver = new Deliver();
        deliver.setDeliver("deliver");
        deliver.setDeliverId(1L);
        deliver = deliverRepository.saveAndFlush(deliver);

        diagnose = new Diagnose();
        diagnose.setNote("note diagnose");
        diagnose.setQuery(query);
        diagnose.setDisease(disease);
        diagnose.setDoctor(doctor);
        diagnose = diagnoseRepository.saveAndFlush(diagnose);
    }

    @Test
    void B5903191_testCreateReferralOK() {
        Referral referral = new Referral();
        referral.setNote("ต้องรีบดำเนินการรักษา");
        referral.setReferralId(1L);
        referral.setDeliver(deliver);
        referral.setForwardTo(forwardTo);
        referral.setForwardType(forwardType);
        referral.setDiagnose(diagnose);
        referral = referralRepository.saveAndFlush(referral);

        Optional<Referral> result = referralRepository.findById(referral.getReferralId());
        assertEquals(1L, result.get().getReferralId());
        assertEquals("ต้องรีบดำเนินการรักษา", result.get().getNote());
    }

    @Test
    void B5903191_testReferralIdMustNotBeNull() {
        Referral referral = new Referral();
        referral.setReferralId(null);
        referral.setDeliver(deliver);
        referral.setForwardTo(forwardTo);
        referral.setForwardType(forwardType);
        referral.setDiagnose(diagnose);
        referral.setNote("ต้องรีบดำเนินการรักษา");

        Set<ConstraintViolation<Referral>> result = validator.validate(referral);

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
        referral.setDeliver(deliver);
        referral.setForwardTo(forwardTo);
        referral.setForwardType(forwardType);
        referral.setDiagnose(diagnose);

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
        referral.setDeliver(deliver);
        referral.setForwardTo(forwardTo);
        referral.setForwardType(forwardType);
        referral.setDiagnose(diagnose);

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
        referral.setDeliver(deliver);
        referral.setForwardTo(forwardTo);
        referral.setForwardType(forwardType);
        referral.setDiagnose(diagnose);
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
        referral.setDeliver(deliver);
        referral.setForwardTo(forwardTo);
        referral.setForwardType(forwardType);
        referral.setDiagnose(diagnose);

        Set<ConstraintViolation<Referral>> result = validator.validate(referral);

        assertEquals(1, result.size());

        ConstraintViolation<Referral> v = result.iterator().next();
        assertEquals("must match \"^[0-9a-zA-Zก-๙\\s]+$\"", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void B5903191_testDeliverMustNotBeNull() {
        Referral referral = new Referral();
        referral.setReferralId(1L);
        referral.setNote("ต้องรีบดำเนินการรักษา");
        referral.setDeliver(null);
        referral.setForwardTo(forwardTo);
        referral.setForwardType(forwardType);
        referral.setDiagnose(diagnose);

        Set<ConstraintViolation<Referral>> result = validator.validate(referral);

        assertEquals(1, result.size());

        ConstraintViolation<Referral> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("deliver", v.getPropertyPath().toString());
    }

    @Test
    void B5903191_testDiagnoseMustNotBeNull() {
        Referral referral = new Referral();
        referral.setReferralId(1L);
        referral.setNote("ต้องรีบดำเนินการรักษา");
        referral.setDeliver(deliver);
        referral.setForwardTo(forwardTo);
        referral.setForwardType(forwardType);
        referral.setDiagnose(null);

        Set<ConstraintViolation<Referral>> result = validator.validate(referral);

        assertEquals(1, result.size());

        ConstraintViolation<Referral> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("diagnose", v.getPropertyPath().toString());
    }

    @Test
    void B5903191_testForwardToMustNotBeNull() {
        Referral referral = new Referral();
        referral.setReferralId(1L);
        referral.setNote("ต้องรีบดำเนินการรักษา");
        referral.setDeliver(deliver);
        referral.setForwardTo(null);
        referral.setForwardType(forwardType);
        referral.setDiagnose(diagnose);

        Set<ConstraintViolation<Referral>> result = validator.validate(referral);

        assertEquals(1, result.size());

        ConstraintViolation<Referral> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("forwardTo", v.getPropertyPath().toString());
    }

    @Test
    void B5903191_testForwardTypeMustNotBeNull() {
        Referral referral = new Referral();
        referral.setReferralId(1L);
        referral.setNote("ต้องรีบดำเนินการรักษา");
        referral.setDeliver(deliver);
        referral.setForwardTo(forwardTo);
        referral.setForwardType(null);
        referral.setDiagnose(diagnose);

        Set<ConstraintViolation<Referral>> result = validator.validate(referral);

        assertEquals(1, result.size());

        ConstraintViolation<Referral> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("forwardType", v.getPropertyPath().toString());
    }
}
