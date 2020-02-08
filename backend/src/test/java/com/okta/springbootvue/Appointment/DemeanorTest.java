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
public class DemeanorTest {

    @Autowired
    private DemeanorRepository demeanorRepository;

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // บันทึกรายการการปฏิบัติตัวสำเร็จ
    @Test
    void B5905188_testDemeanorSuccess() {
        Demeanor demeanor = new Demeanor();
        demeanor.setDemeanor("งดเครื่องดื่ม งดอาหาร อย่างน้อย 8-10 ชั่วโมงก่อนการตรวจ");
        
        demeanor = demeanorRepository.saveAndFlush(demeanor);

        Optional<Demeanor> found = demeanorRepository.findById(demeanor.getDemeanorId());
        assertEquals("งดเครื่องดื่ม งดอาหาร อย่างน้อย 8-10 ชั่วโมงก่อนการตรวจ", found.get().getDemeanor());
    }

    // รายการการปฏิบัติตัวห้ามเป็น null
    @Test
    void B5905188_testDemeanorMustNotBeNull() {
        Demeanor demeanor = new Demeanor();
        demeanor.setDemeanor(null);
        
        Set<ConstraintViolation<Demeanor>> result = validator.validate(demeanor);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Demeanor> found = result.iterator().next();
        assertEquals("must not be null", found.getMessage());
        assertEquals("demeanor", found.getPropertyPath().toString());
    }
}