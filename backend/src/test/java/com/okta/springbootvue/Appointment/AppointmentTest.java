package com.okta.springbootvue.Appointment;

import com.okta.springbootvue.Appointment.Entity.*;
import com.okta.springbootvue.Appointment.Repository.*;
import com.okta.springbootvue.Diagnose.entity.*;
import com.okta.springbootvue.Diagnose.repository.*;

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
public class AppointmentTest {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private DemeanorRepository demeanorRepository;
    @Autowired
    private ReasonRepository reasonRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DiagnoseRepository diagnoseRepository;

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B5905188_testAppointmentSuccess() {
        Diagnose diagnose = new Diagnose();
        diagnose.setNameRegister("ศานันทินี");
        diagnoseRepository.saveAndFlush(diagnose);

        Clinic clinic = new Clinic();
        clinic.setClinic("อายุรกรรมทั่วไป");
        clinicRepository.saveAndFlush(clinic);

        Demeanor demeanor = new Demeanor();
        demeanor.setDemeanor("งดดื่มแอลกอฮอล์ อย่างน้อย 24 ชั่วโมงก่อนการตรวจ");
        demeanorRepository.saveAndFlush(demeanor);

        Reason reason = new Reason();
        reason.setReason("ตรวจโรค");
        reasonRepository.saveAndFlush(reason);

        Doctor doctor = new Doctor();
        doctor.setName("นอนน้อย นอนนะ");
        doctorRepository.saveAndFlush(doctor);

        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate("12-02-2563");
        appoint.setAppointTime("10:30");
        appoint.setClinic(clinic);
        appoint.setDemeanor(demeanor);
        appoint.setReason(reason);        
        appoint.setDoctor(doctor);

        appoint = appointmentRepository.saveAndFlush(appoint);

        Optional<Appointment> found = appointmentRepository.findById(appoint.getAppointmentId());
        assertEquals(diagnose, found.get().getDiagnose());
        assertEquals("12-02-2563", found.get().getAppointDate());
        assertEquals("10:30", found.get().getAppointTime());
        assertEquals(clinic, found.get().getClinic());
        assertEquals(demeanor, found.get().getDemeanor());
        assertEquals(reason, found.get().getReason());
        assertEquals(doctor, found.get().getDoctor());
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B5905188_testAppointTimeMustNotBeNull() {
        Diagnose diagnose = new Diagnose();
        diagnose.setNameRegister("ศานันทินี");
        diagnoseRepository.saveAndFlush(diagnose);

        Clinic clinic = new Clinic();
        clinic.setClinic("อายุรกรรมทั่วไป");
        clinicRepository.saveAndFlush(clinic);

        Demeanor demeanor = new Demeanor();
        demeanor.setDemeanor("งดดื่มแอลกอฮอล์ อย่างน้อย 24 ชั่วโมงก่อนการตรวจ");
        demeanorRepository.saveAndFlush(demeanor);

        Reason reason = new Reason();
        reason.setReason("ตรวจโรค");
        reasonRepository.saveAndFlush(reason);

        Doctor doctor = new Doctor();
        doctor.setName("นอนน้อย นอนนะ");
        doctorRepository.saveAndFlush(doctor);

        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate("12-02-2563");
        appoint.setAppointTime(null);
        appoint.setClinic(clinic);
        appoint.setDemeanor(demeanor);
        appoint.setReason(reason);        
        appoint.setDoctor(doctor);

        Set<ConstraintViolation<Appointment>> result = validator.validate(appoint);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> found = result.iterator().next();
        assertEquals("must not be null", found.getMessage());
        assertEquals("appointTime", found.getPropertyPath().toString());
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B5905188_testAppointDateMustNotMatchPattern() {
        Diagnose diagnose = new Diagnose();
        diagnose.setNameRegister("ศานันทินี");
        diagnoseRepository.saveAndFlush(diagnose);

        Clinic clinic = new Clinic();
        clinic.setClinic("อายุรกรรมทั่วไป");
        clinicRepository.saveAndFlush(clinic);

        Demeanor demeanor = new Demeanor();
        demeanor.setDemeanor("งดดื่มแอลกอฮอล์ อย่างน้อย 24 ชั่วโมงก่อนการตรวจ");
        demeanorRepository.saveAndFlush(demeanor);

        Reason reason = new Reason();
        reason.setReason("ตรวจโรค");
        reasonRepository.saveAndFlush(reason);

        Doctor doctor = new Doctor();
        doctor.setName("นอนน้อย นอนนะ");
        doctorRepository.saveAndFlush(doctor);

        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate("12.02.2563");
        appoint.setAppointTime("10:30");
        appoint.setClinic(clinic);
        appoint.setDemeanor(demeanor);
        appoint.setReason(reason);        
        appoint.setDoctor(doctor);

        Set<ConstraintViolation<Appointment>> result = validator.validate(appoint);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> found = result.iterator().next();
        assertEquals("must match \"^\\d{2}-\\d{2}-\\d{4}$\"", found.getMessage());
        assertEquals("appointDate", found.getPropertyPath().toString());
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B5905188_testAppointTimeMustNotMatchPattern() {
        Diagnose diagnose = new Diagnose();
        diagnose.setNameRegister("ศานันทินี");
        diagnoseRepository.saveAndFlush(diagnose);

        Clinic clinic = new Clinic();
        clinic.setClinic("อายุรกรรมทั่วไป");
        clinicRepository.saveAndFlush(clinic);

        Demeanor demeanor = new Demeanor();
        demeanor.setDemeanor("งดดื่มแอลกอฮอล์ อย่างน้อย 24 ชั่วโมงก่อนการตรวจ");
        demeanorRepository.saveAndFlush(demeanor);

        Reason reason = new Reason();
        reason.setReason("ตรวจโรค");
        reasonRepository.saveAndFlush(reason);

        Doctor doctor = new Doctor();
        doctor.setName("นอนน้อย นอนนะ");
        doctorRepository.saveAndFlush(doctor);

        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate("12-02-2563");
        appoint.setAppointTime("10.30");
        appoint.setClinic(clinic);
        appoint.setDemeanor(demeanor);
        appoint.setReason(reason);        
        appoint.setDoctor(doctor);

        Set<ConstraintViolation<Appointment>> result = validator.validate(appoint);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> found = result.iterator().next();
        assertEquals("must match \"^\\d{2}:\\d{2}$\"", found.getMessage());
        assertEquals("appointTime", found.getPropertyPath().toString());
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B5905188_testAppointTimeMustNotLessThan5() {
        Diagnose diagnose = new Diagnose();
        diagnose.setNameRegister("ศานันทินี");
        diagnoseRepository.saveAndFlush(diagnose);

        Clinic clinic = new Clinic();
        clinic.setClinic("อายุรกรรมทั่วไป");
        clinicRepository.saveAndFlush(clinic);

        Demeanor demeanor = new Demeanor();
        demeanor.setDemeanor("งดดื่มแอลกอฮอล์ อย่างน้อย 24 ชั่วโมงก่อนการตรวจ");
        demeanorRepository.saveAndFlush(demeanor);

        Reason reason = new Reason();
        reason.setReason("ตรวจโรค");
        reasonRepository.saveAndFlush(reason);

        Doctor doctor = new Doctor();
        doctor.setName("นอนน้อย นอนนะ");
        doctorRepository.saveAndFlush(doctor);

        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate("12-02-2563");
        appoint.setAppointTime("9:30");
        appoint.setClinic(clinic);
        appoint.setDemeanor(demeanor);
        appoint.setReason(reason);        
        appoint.setDoctor(doctor);

        Set<ConstraintViolation<Appointment>> result = validator.validate(appoint);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> found = result.iterator().next();
        assertEquals("must match \"^\\d{2}:\\d{2}$\"", found.getMessage());
        assertEquals("appointTime", found.getPropertyPath().toString());
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B5905188_testClinicMustNotBeNull() {
        Diagnose diagnose = new Diagnose();
        diagnose.setNameRegister("ศานันทินี");
        diagnoseRepository.saveAndFlush(diagnose);

        Clinic clinic = new Clinic();
        clinic.setClinic("อายุรกรรมทั่วไป");
        clinicRepository.saveAndFlush(clinic);

        Demeanor demeanor = new Demeanor();
        demeanor.setDemeanor("งดดื่มแอลกอฮอล์ อย่างน้อย 24 ชั่วโมงก่อนการตรวจ");
        demeanorRepository.saveAndFlush(demeanor);

        Reason reason = new Reason();
        reason.setReason("ตรวจโรค");
        reasonRepository.saveAndFlush(reason);

        Doctor doctor = new Doctor();
        doctor.setName("นอนน้อย นอนนะ");
        doctorRepository.saveAndFlush(doctor);

        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate("12-02-2563");
        appoint.setAppointTime("10:30");
        appoint.setClinic(null);
        appoint.setDemeanor(demeanor);
        appoint.setReason(reason);        
        appoint.setDoctor(doctor);

        Set<ConstraintViolation<Appointment>> result = validator.validate(appoint);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> found = result.iterator().next();
        assertEquals("must not be null", found.getMessage());
        assertEquals("clinic", found.getPropertyPath().toString());
    }
}