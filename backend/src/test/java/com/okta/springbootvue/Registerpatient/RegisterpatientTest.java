package com.okta.springbootvue.Registerpatient;

import com.okta.springbootvue.Registerpatient.Entity.Gender;
import com.okta.springbootvue.Registerpatient.Entity.NameTitle;
import com.okta.springbootvue.Registerpatient.Entity.Province;
import com.okta.springbootvue.Registerpatient.Entity.Registerpatient;
import com.okta.springbootvue.Registerpatient.Repository.GenderRepository;
import com.okta.springbootvue.Registerpatient.Repository.NameTitleRepository;
import com.okta.springbootvue.Registerpatient.Repository.ProvinceRepository;
import com.okta.springbootvue.Registerpatient.Repository.RegisterpatientRepository;

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
public class RegisterpatientTest {

    private Validator validator;

    @Autowired
    private RegisterpatientRepository registerpatientRepository;
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
    }

    // FirstName กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5900985_testFirstNamewithCorrect() {
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
        Optional<Registerpatient> check = registerpatientRepository.findById(r.getRegisterId());
        assertEquals("กกกกกก", check.get().getFirstName());
        assertEquals("ขขขขขข", check.get().getLastName());
        assertEquals(33, check.get().getAge());
        assertEquals(70, check.get().getWeight());
        assertEquals(170, check.get().getHeight());
        assertEquals("Address", check.get().getAddressDetail());
        assertEquals("0819658742", check.get().getMobilePhone());
        assertEquals(gender, check.get().getGender());
        assertEquals(nameTitle, check.get().getNameTitle());
        assertEquals(province, check.get().getProvince());
    }

    // FirstName กรณีที่ 2 น้อยกว่า 4 ตัว
    @Test
    void B5900985_testFirstNameSizelessthan5() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกก");
        r.setLastName("ขขขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("firstName", v.getPropertyPath().toString());
    }

    // FirstName กรณีที่ 3 มากกว่า 30 ตัว
    @Test
    void B5900985_testFirstNameSizeMorethan30() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกกกกกกกกกกกกกกกกกกกกกกกกกกกกก");
        r.setLastName("ขขขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("firstName", v.getPropertyPath().toString());
    }

    // FirstName กรณีที่ 4 ชื่อต้องไม่ว่างเปล่า
    @Test
    void B5900985_testFirstNameMustNotBeNull() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setLastName("ขขขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("firstName", v.getPropertyPath().toString());
    }

    // LastName กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5900985_testLastNamewithCorrect() {
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
        Optional<Registerpatient> check = registerpatientRepository.findById(r.getRegisterId());
        assertEquals("กกกกกก", check.get().getFirstName());
        assertEquals("ขขขขขข", check.get().getLastName());
        assertEquals(33, check.get().getAge());
        assertEquals(70, check.get().getWeight());
        assertEquals(170, check.get().getHeight());
        assertEquals("Address", check.get().getAddressDetail());
        assertEquals("0819658742", check.get().getMobilePhone());
        assertEquals(gender, check.get().getGender());
        assertEquals(nameTitle, check.get().getNameTitle());
        assertEquals(province, check.get().getProvince());
    }

    // LastName กรณีที่ 2 น้อยกว่า 4 ตัว
    @Test
    void B5900985_testLastNameSizelessthan5() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("lastName", v.getPropertyPath().toString());
    }

    // LastName กรณีที่ 3 มากกว่า 30 ตัว
    @Test
    void B5900985_testLastNameSizeMorethan30() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขขขขขขขขขขขขขขขขขขขขขขขขขขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("lastName", v.getPropertyPath().toString());
    }

    // LastName กรณีที่ 4 ชื่อต้องไม่ว่างเปล่า
    @Test
    void B5900985_testLastNameMustNotBeNull() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("lastName", v.getPropertyPath().toString());
    }

    // Age กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5900985_testAgewithCorrect() {
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
        Optional<Registerpatient> check = registerpatientRepository.findById(r.getRegisterId());
        assertEquals("กกกกกก", check.get().getFirstName());
        assertEquals("ขขขขขข", check.get().getLastName());
        assertEquals(33, check.get().getAge());
        assertEquals(70, check.get().getWeight());
        assertEquals(170, check.get().getHeight());
        assertEquals("Address", check.get().getAddressDetail());
        assertEquals("0819658742", check.get().getMobilePhone());
        assertEquals(gender, check.get().getGender());
        assertEquals(nameTitle, check.get().getNameTitle());
        assertEquals(province, check.get().getProvince());
    }

    // Age กรณีที่ 2 อายุไม่ถึง 15 ปี
    @Test
    void B5900985_testAgemin15() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(2);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must be greater than or equal to 15", v.getMessage());
        assertEquals("age", v.getPropertyPath().toString());
    }

    // Age กรณีที่ 3 อายุไม่เกิน 150 ปี
    @Test
    void B5900985_testAgemax150() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(151);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must be less than or equal to 150", v.getMessage());
        assertEquals("age", v.getPropertyPath().toString());
    }

    // Age กรณีที่ 4
    @Test
    void B5900985_testAgeMustNotBeNull() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("age", v.getPropertyPath().toString());
    }

    // Weight กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5900985_testWeightwithCorrect() {
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
        Optional<Registerpatient> check = registerpatientRepository.findById(r.getRegisterId());
        assertEquals("กกกกกก", check.get().getFirstName());
        assertEquals("ขขขขขข", check.get().getLastName());
        assertEquals(33, check.get().getAge());
        assertEquals(70, check.get().getWeight());
        assertEquals(170, check.get().getHeight());
        assertEquals("Address", check.get().getAddressDetail());
        assertEquals("0819658742", check.get().getMobilePhone());
        assertEquals(gender, check.get().getGender());
        assertEquals(nameTitle, check.get().getNameTitle());
        assertEquals(province, check.get().getProvince());
    }

    // Weight กรณีที่ 2 นนไม่ถึง 0
    @Test
    void B5900985_testWeightmin0() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(16);
        r.setWeight(0);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("weight", v.getPropertyPath().toString());
    }

    // Weight กรณีที่ 3 นนเกิน 200
    @Test
    void B5900985_testWeightmax200() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(16);
        r.setWeight(201);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("weight", v.getPropertyPath().toString());
    }

    // Weight กรณีที่ 4
    @Test
    void B5900985_testWeightMustNotBeNull() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(16);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("weight", v.getPropertyPath().toString());
    }

    // Height กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5900985_testHeightwithCorrect() {
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
        Optional<Registerpatient> check = registerpatientRepository.findById(r.getRegisterId());
        assertEquals("กกกกกก", check.get().getFirstName());
        assertEquals("ขขขขขข", check.get().getLastName());
        assertEquals(33, check.get().getAge());
        assertEquals(70, check.get().getWeight());
        assertEquals(170, check.get().getHeight());
        assertEquals("Address", check.get().getAddressDetail());
        assertEquals("0819658742", check.get().getMobilePhone());
        assertEquals(gender, check.get().getGender());
        assertEquals(nameTitle, check.get().getNameTitle());
        assertEquals(province, check.get().getProvince());
    }

    // Height กรณีที่ 2 ส่วนสูงไม่ถึง 1
    @Test
    void B5900985_testHeightmin0() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(16);
        r.setWeight(50);
        r.setHeight(0);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("height", v.getPropertyPath().toString());
    }

    // Height กรณีที่ 3 ส่วนสูงเกิน 200
    @Test
    void B5900985_testHeightmax200() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(16);
        r.setWeight(50);
        r.setHeight(210);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must be less than or equal to 200", v.getMessage());
        assertEquals("height", v.getPropertyPath().toString());
    }

    // Height กรณีที่ 4
    @Test
    void B5900985_testHeightMustNotBeNull() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(16);
        r.setWeight(50);
        r.setAddressDetail("Address");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("height", v.getPropertyPath().toString());
    }

    // AddressDeta กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5900985_testAddressDetawithCorrect() {
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
        Optional<Registerpatient> check = registerpatientRepository.findById(r.getRegisterId());
        assertEquals("กกกกกก", check.get().getFirstName());
        assertEquals("ขขขขขข", check.get().getLastName());
        assertEquals(33, check.get().getAge());
        assertEquals(70, check.get().getWeight());
        assertEquals(170, check.get().getHeight());
        assertEquals("Address", check.get().getAddressDetail());
        assertEquals("0819658742", check.get().getMobilePhone());
        assertEquals(gender, check.get().getGender());
        assertEquals(nameTitle, check.get().getNameTitle());
        assertEquals(province, check.get().getProvince());
    }

    // AddressDeta กรณีที่ 2 น้อยกว่า 5 ตัว
    @Test
    void B5900985_testAddressDetailSizelessthan5() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("32aa");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("size must be between 5 and 50", v.getMessage());
        assertEquals("addressDetail", v.getPropertyPath().toString());
    }

    // AddressDeta กรณีที่ 3 มากกว่า 50 ตัว
    @Test
    void B5900985_testAddressDetailSizeMorethan50() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("32ถ.หนองสาหร่ายต.หนองสาหร่ายอ.ปากช่อง1111111111111111111");
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("size must be between 5 and 50", v.getMessage());
        assertEquals("addressDetail", v.getPropertyPath().toString());
    }

    // AddressDeta กรณีที่ 4 ชื่อต้องไม่ว่างเปล่า
    @Test
    void B5900985_testAddressDetailMustNotBeNull() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setMobilePhone("0819658742");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("addressDetail", v.getPropertyPath().toString());
    }

    // MobilePhone กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
    @Test
    void B5900985_testMobilePhonewithCorrect() {
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
        Optional<Registerpatient> check = registerpatientRepository.findById(r.getRegisterId());
        assertEquals("กกกกกก", check.get().getFirstName());
        assertEquals("ขขขขขข", check.get().getLastName());
        assertEquals(33, check.get().getAge());
        assertEquals(70, check.get().getWeight());
        assertEquals(170, check.get().getHeight());
        assertEquals("Address", check.get().getAddressDetail());
        assertEquals("0819658742", check.get().getMobilePhone());
        assertEquals(gender, check.get().getGender());
        assertEquals(nameTitle, check.get().getNameTitle());
        assertEquals(province, check.get().getProvince());
    }

    // MobilePhone กรณีที่ 2 น้อยกว่า 10 ตัว
    @Test
    void B5900985_testMobilePhoneSizelessthan10() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("098789754");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("mobilePhone", v.getPropertyPath().toString());
    }

    // MobilePhone กรณีที่ 3 มากกว่า 10 ตัว
    @Test
    void B5900985_testMobilePhoneSizelessthan11() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setMobilePhone("09878975400");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("mobilePhone", v.getPropertyPath().toString());
    }

    // MobilePhone กรณีที่ 4
    @Test
    void B5900985_testMobilePhoneMustNotBeNull() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);

        Registerpatient r = new Registerpatient();
        r.setFirstName("กกกกก");
        r.setLastName("ขขขขข");
        r.setAge(33);
        r.setWeight(70);
        r.setHeight(170);
        r.setAddressDetail("Address");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setProvince(province);

        Set<ConstraintViolation<Registerpatient>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Registerpatient> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("mobilePhone", v.getPropertyPath().toString());
    }

}