package com.okta.springbootvue.Appointment;

import com.okta.springbootvue.Appointment.Entity.*;
import com.okta.springbootvue.Appointment.Repository.*;
import com.okta.springbootvue.Diagnose.entity.*;
import com.okta.springbootvue.Diagnose.repository.*;
import com.okta.springbootvue.Query.Entity.*;
import com.okta.springbootvue.Query.Repository.*;
import com.okta.springbootvue.Registerpatient.Entity.*;
import com.okta.springbootvue.Registerpatient.Repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Date;
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
    Appointment appointment = new Appointment();
    Clinic clinic = new Clinic();
    Demeanor demeanor = new Demeanor();
    Reason reason = new Reason();
    Doctor doctor = new Doctor();
    Date Date = new Date();
    

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Gender gender = new Gender();
        gender.setGender("หญิง");
        genderRepository.saveAndFlush(gender);

        NameTitle nameTitle = new NameTitle();
        nameTitle.setNametitle("นางสาว");
        nameTitleRepository.saveAndFlush(nameTitle);

        Province province = new Province();
        province.setProvince("เชียงใหม่");
        provinceRepository.saveAndFlush(province);

        Registerpatient r = new Registerpatient();
        r.setFirstName("ศานันทินี");
        r.setLastName("ชวธนากร");
        r.setAge(22);
        r.setWeight(65);
        r.setHeight(165);
        r.setAddressDetail("บ้านปง หมู่7 ต.อินทขิล อ.แม่แตง");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);
        registerpatientRepository.saveAndFlush(r);

        Duration duration = new Duration();
        duration.setDuration("1 วัน");
        durationRepository.saveAndFlush(duration);

        CongenitalDisease congenitalDisease = new CongenitalDisease();
        congenitalDisease.setCongenitalDisease("ไม่มีโรคประจำตัว");
        congenitalDiseaseRepository.saveAndFlush(congenitalDisease);

        Query query = new Query();
        query.setRegisterpatient(r);
        query.setCongenitalDisease(congenitalDisease);
        query.setDuration(duration);
        query.setPressureDIA(123);
        query.setPressureSYS(123);
        query.setSymptom("ปวดท้อง");
        query.setTemperature(37.5f);
        queryRepository.saveAndFlush(query);

        Disease disease = new Disease();
        disease.setName("กรดไหลย้อน");
        diseaseRepository.saveAndFlush(disease);

        doctor.setName("นอนน้อย นอนนะ");
        doctorRepository.saveAndFlush(doctor);

        diagnose.setQuery(query);
        diagnose.setNote("กินหมูกะทะ");
        diagnose.setDisease(disease);
        diagnose.setDoctor(doctor);
        diagnoseRepository.saveAndFlush(diagnose);

        clinic.setClinic("อายุรกรรมทั่วไป");
        clinicRepository.saveAndFlush(clinic);

        demeanor.setDemeanor("ไม่ต้อง");
        demeanorRepository.saveAndFlush(demeanor);

        reason.setReason("เจาะเลือด");
        reasonRepository.saveAndFlush(reason);

        
    }

    // บันทึกการนัดหมายสำเร็จ
    @Test
    void B5905188_testAppointmentSuccess() {
        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate(Date);
        appoint.setAppointTime("10:30");
        appoint.setClinic(clinic);
        appoint.setDemeanor(demeanor);
        appoint.setReason(reason);        
        appoint.setDoctor(doctor);

        appoint = appointmentRepository.saveAndFlush(appoint);

        Optional<Appointment> found = appointmentRepository.findById(appoint.getAppointmentId());
        assertEquals(diagnose, found.get().getDiagnose());
        assertEquals(Date, found.get().getAppointDate());
        assertEquals("10:30", found.get().getAppointTime());
        assertEquals(clinic, found.get().getClinic());
        assertEquals(demeanor, found.get().getDemeanor());
        assertEquals(reason, found.get().getReason());
        assertEquals(doctor, found.get().getDoctor());
    }

    // วันที่ห้ามเป็น null
    @Test
    void B5905188_testAppointDateMustNotBeNull() {
        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate(null);
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
        assertEquals("must not be null", found.getMessage());
        assertEquals("appointDate", found.getPropertyPath().toString());
    }

    // เวลาห้ามเป็น null
    @Test
    void B5905188_testAppointTimeMustNotBeNull() {
        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate(Date);
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

    // รูปแบบเวลาต้องมี : คั่นระหว่างชั่วโมงกับนาที
    @Test
    void B5905188_testAppointTimeMustNotMatchPattern() {
        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate(Date);
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

    // รูปแบบเวลาไม่น้อยกว่า 5 ตัว
    @Test
    void B5905188_testAppointTimeMustNotLessThan5() {
        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate(Date);
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

    // คลินิกห้ามเป็น null
    @Test
    void B5905188_testClinicMustNotBeNull() {
        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate(Date);
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

    // การปฏิบัติตัวห้ามเป็น null
    @Test
    void B5905188_testDemeanorMustNotBeNull() {
        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate(Date);
        appoint.setAppointTime("10:30");
        appoint.setClinic(clinic);
        appoint.setDemeanor(null);
        appoint.setReason(reason);        
        appoint.setDoctor(doctor);

        Set<ConstraintViolation<Appointment>> result = validator.validate(appoint);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> found = result.iterator().next();
        assertEquals("must not be null", found.getMessage());
        assertEquals("demeanor", found.getPropertyPath().toString());
    }

    // เหตุผลห้ามเป็น null
    @Test
    void B5905188_testReasonMustNotBeNull() {
        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate(Date);
        appoint.setAppointTime("10:30");
        appoint.setClinic(clinic);
        appoint.setDemeanor(demeanor);
        appoint.setReason(null);        
        appoint.setDoctor(doctor);

        Set<ConstraintViolation<Appointment>> result = validator.validate(appoint);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> found = result.iterator().next();
        assertEquals("must not be null", found.getMessage());
        assertEquals("reason", found.getPropertyPath().toString());
    }

    // แพทย์ห้ามเป็น null
    @Test
    void B5905188_testDoctorMustNotBeNull() {
        Appointment appoint = new Appointment();
        appoint.setDiagnose(diagnose);
        appoint.setAppointDate(Date);
        appoint.setAppointTime("10:30");
        appoint.setClinic(clinic);
        appoint.setDemeanor(demeanor);
        appoint.setReason(reason);        
        appoint.setDoctor(null);

        Set<ConstraintViolation<Appointment>> result = validator.validate(appoint);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Appointment> found = result.iterator().next();
        assertEquals("must not be null", found.getMessage());
        assertEquals("doctor", found.getPropertyPath().toString());
    }

    // ชื่อผู้ป่วยห้ามเป็น null
    @Test
    void B5905188_testDiagnoseMustNotBeNull() {
        Appointment appoint = new Appointment();
        appoint.setDiagnose(null);
        appoint.setAppointDate(Date);
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
        assertEquals("must not be null", found.getMessage());
        assertEquals("diagnose", found.getPropertyPath().toString());
    }
}