package com.okta.springbootvue.RegisterDeaths;

import com.okta.springbootvue.Registerpatient.Entity.Gender;
import com.okta.springbootvue.Registerpatient.Entity.NameTitle;
import com.okta.springbootvue.Registerpatient.Entity.Province;
import com.okta.springbootvue.RegisterDeaths.Entity.CauseofDeath;
import com.okta.springbootvue.RegisterDeaths.Entity.Place;
import com.okta.springbootvue.RegisterDeaths.Entity.RegisterDeaths;
import com.okta.springbootvue.RegisterDeaths.Repository.PlaceRepository;
import com.okta.springbootvue.RegisterDeaths.Repository.RegisterDeathsRepository;
import com.okta.springbootvue.RegisterDeaths.Repository.CauseofDeathRepository;
import com.okta.springbootvue.Registerpatient.Repository.GenderRepository;
import com.okta.springbootvue.Registerpatient.Repository.NameTitleRepository;
import com.okta.springbootvue.Registerpatient.Repository.ProvinceRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Set;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RegisterDeathsTest {

    private Validator validator;

    @Autowired
    private RegisterDeathsRepository registerDeathsRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private CauseofDeathRepository causeofDeathRepository;
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
void B5905836_testFirstNamewithCorrect() {
    Gender gender = genderRepository.findById(1);
    NameTitle nameTitle = nameTitleRepository.findById(1);
    Province province = provinceRepository.findById(1);
    Place place = placeRepository.findById(1);
    CauseofDeath causeofDeath = causeofDeathRepository.findById(1);

    RegisterDeaths re = new RegisterDeaths();
    Date Date = new Date();
    re.setFirstName("กดกดกด");
    re.setLastName("ดกดกดก");
    re.setAge(63);
    re.setBorn(Date);
    re.setDeath(Date);
    re.setAddressDetail("Address");
    re.setMobilePhone("0897654321");
    re.setGender(gender);
    re.setNameTitle(nameTitle);
    re.setPlace(place);
    re.setCauseofDeath(causeofDeath);
    re.setProvince(province);

    registerDeathsRepository.save(re);

        Optional<RegisterDeaths> check = registerDeathsRepository.findById(re.getRegisterDeathsId());
        assertEquals("กดกดกด", check.get().getFirstName());
        assertEquals("ดกดกดก", check.get().getLastName());
        assertEquals(63, check.get().getAge());
        assertEquals(Date, check.get().getBorn());
        assertEquals(Date, check.get().getDeath());
        assertEquals("Address", check.get().getAddressDetail());
        assertEquals("0897654321", check.get().getMobilePhone());
        assertEquals(gender, check.get().getGender());
        assertEquals(nameTitle, check.get().getNameTitle());
        assertEquals(place, check.get().getPlace());
        assertEquals(causeofDeath, check.get().getCauseofDeath());
        assertEquals(province, check.get().getProvince());
}

    // FirstName กรณีที่ 2 น้อยกว่า 4 ตัว
    @Test
    void B5905936_testFirstNameSizelessthan5() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Place place = placeRepository.findById(1);
        CauseofDeath causeofDeath = causeofDeathRepository.findById(1);

        RegisterDeaths r = new RegisterDeaths();
        Date Date = new Date();
        r.setFirstName("กด");
        r.setLastName("ดกดกดก");
        r.setAge(63);
        r.setBorn(Date);
        r.setDeath(Date);
        r.setAddressDetail("Address");
        r.setMobilePhone("0897654321");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setPlace(place);
        r.setCauseofDeath(causeofDeath);
        r.setProvince(province);

        Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterDeaths> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("firstName", v.getPropertyPath().toString());
    }

    // FirstName กรณีที่ 3 มากกว่า 30 ตัว
    @Test
    void B5905836_testFirstNameSizeMorethan30() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Place place = placeRepository.findById(1);
        CauseofDeath causeofDeath = causeofDeathRepository.findById(1);

        RegisterDeaths r = new RegisterDeaths();
        Date Date = new Date();
        r.setFirstName("กดกดกดกดกดกดกดกดกดกดกดกดกดกดกดกด");
        r.setLastName("ดกดกดก");
        r.setAge(63);       
        r.setBorn(Date);
        r.setDeath(Date);
        r.setAddressDetail("Address");
        r.setMobilePhone("0897654321");
        r.setGender(gender);
        r.setNameTitle(nameTitle);
        r.setPlace(place);
        r.setCauseofDeath(causeofDeath);
        r.setProvince(province);


        Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<RegisterDeaths> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("firstName", v.getPropertyPath().toString());
    }

    // FirstName กรณีที่ 4 ชื่อต้องไม่ว่างเปล่า
         @Test
         void B5905836_testFirstNameMustNotBeNull() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


             RegisterDeaths r = new RegisterDeaths();
             Date Date = new Date();
             r.setFirstName(null);
             r.setLastName("ดกดกดก");
             r.setAge(63);
             r.setBorn(Date);
             r.setDeath(Date);
             r.setAddressDetail("Address");
             r.setMobilePhone("0897654321");
             r.setGender(gender);
             r.setNameTitle(nameTitle);
             r.setPlace(place);
             r.setCauseofDeath(causeofDeath);
             r.setProvince(province);

             Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

             // result ต้องมี error 1 ค่าเท่านั้น
             assertEquals(1, result.size());

             // error message ตรงชนิด และถูก field
             ConstraintViolation<RegisterDeaths> v = result.iterator().next();
             assertEquals("must not be null", v.getMessage());
             assertEquals("firstName", v.getPropertyPath().toString());
         }

    // LastName กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
        @Test
        void B5905836_testLastNamewithCorrect() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


            RegisterDeaths r = new RegisterDeaths();
            Date Date = new Date();
            r.setFirstName("กดกดกด");
            r.setLastName("ดกดกดก");
            r.setAge(63);
            r.setBorn(Date);
            r.setDeath(Date);
            r.setAddressDetail("Address");
            r.setMobilePhone("0897654321");
            r.setGender(gender);
            r.setNameTitle(nameTitle);
            r.setPlace(place);
            r.setCauseofDeath(causeofDeath);
            r.setProvince(province);


            registerDeathsRepository.saveAndFlush(r);
                Optional<RegisterDeaths> check = registerDeathsRepository.findById(r.getRegisterDeathsId());
                assertEquals("กดกดกด", check.get().getFirstName());
                assertEquals("ดกดกดก", check.get().getLastName());
                assertEquals(63, check.get().getAge());
                assertEquals(Date, check.get().getBorn());
                assertEquals(Date, check.get().getDeath());
                assertEquals("Address", check.get().getAddressDetail());
                assertEquals("0897654321", check.get().getMobilePhone());
                assertEquals(gender, check.get().getGender());
                assertEquals(nameTitle, check.get().getNameTitle());
                assertEquals(place, check.get().getPlace());
                assertEquals(causeofDeath, check.get().getCauseofDeath());
        }

    // LastName กรณีที่ 2 น้อยกว่า 4 ตัว
            @Test
            void B5905836_testLastNameSizelessthan5() {
                Gender gender = genderRepository.findById(1);
                NameTitle nameTitle = nameTitleRepository.findById(1);
                Province province = provinceRepository.findById(1);
                Place place = placeRepository.findById(1);
                CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


                RegisterDeaths r = new RegisterDeaths();
                Date Date = new Date();
                r.setFirstName("กดกดกด");
                r.setLastName("ดก");
                r.setAge(63);
                r.setBorn(Date);
                r.setDeath(Date);
                r.setAddressDetail("Address");
                r.setMobilePhone("0897654321");
                r.setGender(gender);
                r.setNameTitle(nameTitle);
                r.setPlace(place);
                r.setCauseofDeath(causeofDeath);
                r.setProvince(province);

                Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

                // result ต้องมี error 1 ค่าเท่านั้น
                assertEquals(1, result.size());

                // error message ตรงชนิด และถูก field
                ConstraintViolation<RegisterDeaths> v = result.iterator().next();
                assertEquals("size must be between 5 and 30", v.getMessage());
                assertEquals("lastName", v.getPropertyPath().toString());
            }


        // LastName กรณีที่ 3 มากกว่า 30 ตัว
        @Test
        void B5905836_testLastNameSizeMorethan30() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


            RegisterDeaths r = new RegisterDeaths();
            Date Date = new Date();
            r.setFirstName("กดกดกด");
            r.setLastName("ดกดกดกดกดกดกดกดกดกดกดกดกดกดกดกดก");
            r.setAge(63);
            r.setBorn(Date);
            r.setDeath(Date);
            r.setAddressDetail("Address");
            r.setMobilePhone("0897654321");
            r.setGender(gender);
            r.setNameTitle(nameTitle);
            r.setPlace(place);
            r.setCauseofDeath(causeofDeath);
            r.setProvince(province);


            Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<RegisterDeaths> v = result.iterator().next();
            assertEquals("size must be between 5 and 30", v.getMessage());
            assertEquals("lastName", v.getPropertyPath().toString());
        }

  // LastName กรณีที่ 4 ชื่อต้องไม่ว่างเปล่า
           @Test
           void B5905836_testLastNameMustNotBeNull() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


             RegisterDeaths r = new RegisterDeaths();
             Date Date = new Date();
             r.setFirstName("กดกดกด");
             r.setLastName(null);
             r.setAge(63);
             r.setBorn(Date);
             r.setDeath(Date);
             r.setAddressDetail("Address");
             r.setMobilePhone("0897654321");
             r.setGender(gender);
             r.setNameTitle(nameTitle);
             r.setPlace(place);
             r.setCauseofDeath(causeofDeath);
             r.setProvince(province);

               Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

               // result ต้องมี error 1 ค่าเท่านั้น
               assertEquals(1, result.size());

               // error message ตรงชนิด และถูก field
               ConstraintViolation<RegisterDeaths> v = result.iterator().next();
               assertEquals("must not be null", v.getMessage());
               assertEquals("lastName", v.getPropertyPath().toString());
           }
        // Age กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
        @Test
        void B5905836_testAgewithCorrect() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


            RegisterDeaths r = new RegisterDeaths();
            Date Date = new Date();
            r.setFirstName("กดกดกด");
            r.setLastName("ดกดกดก");
            r.setAge(63);
            r.setBorn(Date);
            r.setDeath(Date);
            r.setAddressDetail("Address");
            r.setMobilePhone("0897654321");
            r.setGender(gender);
            r.setNameTitle(nameTitle);
            r.setPlace(place);
            r.setCauseofDeath(causeofDeath);


            registerDeathsRepository.saveAndFlush(r);
                Optional<RegisterDeaths> check = registerDeathsRepository.findById(r.getRegisterDeathsId());
                assertEquals("กดกดกด", check.get().getFirstName());
                assertEquals("ดกดกดก", check.get().getLastName());
                assertEquals(63, check.get().getAge());
                assertEquals(Date, check.get().getBorn());
                assertEquals(Date, check.get().getDeath());
                assertEquals("Address", check.get().getAddressDetail());
                assertEquals("0897654321", check.get().getMobilePhone());
                assertEquals(gender, check.get().getGender());
                assertEquals(nameTitle, check.get().getNameTitle());
                assertEquals(place, check.get().getPlace());
                assertEquals(causeofDeath, check.get().getCauseofDeath());
        }

      // Age กรณีที่ 2 อายุไม่ถึง 1  ปี
      @Test
      void B5905836_testAgemin1() {
        Gender gender = genderRepository.findById(1);
        NameTitle nameTitle = nameTitleRepository.findById(1);
        Province province = provinceRepository.findById(1);
        Place place = placeRepository.findById(1);
        CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


          RegisterDeaths r = new RegisterDeaths();
          Date Date = new Date();
          r.setFirstName("กกกกก");
          r.setLastName("ขขขขข");
          r.setAge(0);
          r.setBorn(Date);
          r.setDeath(Date);
          r.setAddressDetail("Address");
          r.setMobilePhone("0897654321");
          r.setGender(gender);
          r.setNameTitle(nameTitle);
          r.setPlace(place);
          r.setCauseofDeath(causeofDeath);


          Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

          // result ต้องมี error 1 ค่าเท่านั้น
          assertEquals(1, result.size());

          // error message ตรงชนิด และถูก field
          ConstraintViolation<RegisterDeaths> v = result.iterator().next();
          assertEquals("must be greater than or equal to 1", v.getMessage());
          assertEquals("age", v.getPropertyPath().toString());
      }

           // Age กรณีที่ 3 อายุไม่เกิน 150 ปี
           @Test
           void B5905836_testAgemax150() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


              RegisterDeaths r = new RegisterDeaths();
              Date Date = new Date();
              r.setFirstName("กกกกก");
              r.setLastName("ขขขขข");
              r.setAge(151);
              r.setBorn(Date);
              r.setDeath(Date);
              r.setAddressDetail("Address");
              r.setMobilePhone("0897654321");
              r.setGender(gender);
              r.setNameTitle(nameTitle);
              r.setPlace(place);
              r.setCauseofDeath(causeofDeath);

               Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

               // result ต้องมี error 1 ค่าเท่านั้น
               assertEquals(1, result.size());

               // error message ตรงชนิด และถูก field
               ConstraintViolation<RegisterDeaths> v = result.iterator().next();
               assertEquals("must be less than or equal to 150", v.getMessage());
               assertEquals("age", v.getPropertyPath().toString());
           }
           // Age กรณีที่ 4 ไม่ว่างเปล่า
           @Test
           void B5905836_testAgeMustNotBeNull() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


               RegisterDeaths r = new RegisterDeaths();
               Date Date = new Date();
               r.setFirstName("กกกกก");
               r.setLastName("ขขขขข");
               r.setAge(null);
               r.setBorn(Date);
               r.setDeath(Date);
               r.setAddressDetail("Address");
               r.setMobilePhone("0897654321");
               r.setGender(gender);
               r.setNameTitle(nameTitle);
               r.setPlace(place);
               r.setCauseofDeath(causeofDeath);
               r.setProvince(province);

               Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

               // result ต้องมี error 1 ค่าเท่านั้น
               assertEquals(1, result.size());

               // error message ตรงชนิด และถูก field
               ConstraintViolation<RegisterDeaths> v = result.iterator().next();
               assertEquals("must not be null", v.getMessage());
               assertEquals("age", v.getPropertyPath().toString());
           }

           // AddressDeta กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
        @Test
        void B5905836_testAddressDetawithCorrect() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


            RegisterDeaths r = new RegisterDeaths();
            Date Date = new Date();
            r.setFirstName("กดกดกด");
            r.setLastName("ดกดกดก");
            r.setAge(63);
            r.setBorn(Date);
            r.setDeath(Date);
            r.setAddressDetail("Address");
            r.setMobilePhone("0897654321");
            r.setGender(gender);
            r.setNameTitle(nameTitle);
            r.setPlace(place);
            r.setCauseofDeath(causeofDeath);
            r.setProvince(province);

            registerDeathsRepository.saveAndFlush(r);
                Optional<RegisterDeaths> check = registerDeathsRepository.findById(r.getRegisterDeathsId());
                assertEquals("กดกดกด", check.get().getFirstName());
                assertEquals("ดกดกดก", check.get().getLastName());
                assertEquals(63, check.get().getAge());
                assertEquals(Date, check.get().getBorn());
                assertEquals(Date, check.get().getDeath());
                assertEquals("Address", check.get().getAddressDetail());
                assertEquals("0897654321", check.get().getMobilePhone());
                assertEquals(gender, check.get().getGender());
                assertEquals(nameTitle, check.get().getNameTitle());
                assertEquals(place, check.get().getPlace());
                assertEquals(causeofDeath, check.get().getCauseofDeath());
        }

        // AddressDeta กรณีที่ 2 น้อยกว่า 5 ตัว
        @Test
        void B5905836_testAddressDetailSizelessthan5() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


            RegisterDeaths r = new RegisterDeaths();
            Date Date = new Date();
            r.setFirstName("กดกดกด");
            r.setLastName("ดกดกดก");
            r.setAge(63);
            r.setBorn(Date);
            r.setDeath(Date);
            r.setAddressDetail("Add");
            r.setMobilePhone("0897654321");
            r.setGender(gender);
            r.setNameTitle(nameTitle);
            r.setPlace(place);
            r.setCauseofDeath(causeofDeath);
            r.setProvince(province);

            Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());

            // error message ตรงชนิด และถูก field
            ConstraintViolation<RegisterDeaths> v = result.iterator().next();
            assertEquals("size must be between 5 and 50", v.getMessage());
            assertEquals("addressDetail", v.getPropertyPath().toString());
        }

         // AddressDeta กรณีที่ 3 มากกว่า 50 ตัว
         @Test
         void B5905836_testAddressDetailSizeMorethan50() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);

            RegisterDeaths r = new RegisterDeaths();
            Date Date = new Date();
            r.setFirstName("กดกดกด");
            r.setLastName("ดกดกดก");
            r.setAge(63);
            r.setBorn(Date);
            r.setDeath(Date);
            r.setAddressDetail("Address12345678901234567890123456789012345678901234567890");
            r.setMobilePhone("0897654321");
            r.setGender(gender);
            r.setNameTitle(nameTitle);
            r.setPlace(place);
            r.setCauseofDeath(causeofDeath);
            r.setProvince(province);

             Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

             // result ต้องมี error 1 ค่าเท่านั้น
             assertEquals(1, result.size());

             // error message ตรงชนิด และถูก field
             ConstraintViolation<RegisterDeaths> v = result.iterator().next();
             assertEquals("size must be between 5 and 50", v.getMessage());
             assertEquals("addressDetail", v.getPropertyPath().toString());
         }

 // Address กรณีที่ 4 ชื่อต้องไม่ว่างเปล่า
          @Test
          void B5905836_testAddressDetailMustNotBeNull() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


             RegisterDeaths r = new RegisterDeaths();
             Date Date = new Date();
             r.setFirstName("กดกดกด");
             r.setLastName("ดกดกดก");
             r.setAge(63);
             r.setBorn(Date);
             r.setDeath(Date);
             r.setAddressDetail(null);
             r.setMobilePhone("0897654321");
             r.setGender(gender);
             r.setNameTitle(nameTitle);
             r.setPlace(place);
             r.setCauseofDeath(causeofDeath);
             r.setProvince(province);

              Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

              // result ต้องมี error 1 ค่าเท่านั้น
              assertEquals(1, result.size());

              // error message ตรงชนิด และถูก field
              ConstraintViolation<RegisterDeaths> v = result.iterator().next();
              assertEquals("must not be null", v.getMessage());
              assertEquals("addressDetail", v.getPropertyPath().toString());
          }

// MobilePhone กรณีที่ 1 ใส่ข้อมูลถูกต้องปกติ
        @Test
        void B5905836_testMobilePhonewithCorrect() {
            Gender gender = genderRepository.findById(1);
            NameTitle nameTitle = nameTitleRepository.findById(1);
            Province province = provinceRepository.findById(1);
            Place place = placeRepository.findById(1);
            CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


            RegisterDeaths r = new RegisterDeaths();
            Date Date = new Date();
            r.setFirstName("กดกดกด");
            r.setLastName("ดกดกดก");
            r.setAge(63);
            r.setBorn(Date);
            r.setDeath(Date);
            r.setAddressDetail("Address");
            r.setMobilePhone("0897654321");
            r.setGender(gender);
            r.setNameTitle(nameTitle);
            r.setPlace(place);
            r.setCauseofDeath(causeofDeath);
            r.setProvince(province);

            registerDeathsRepository.saveAndFlush(r);
                Optional<RegisterDeaths> check = registerDeathsRepository.findById(r.getRegisterDeathsId());
                assertEquals("กดกดกด", check.get().getFirstName());
                assertEquals("ดกดกดก", check.get().getLastName());
                assertEquals(63, check.get().getAge());
                assertEquals(Date, check.get().getBorn());
                assertEquals(Date, check.get().getDeath());
                assertEquals("Address", check.get().getAddressDetail());
                assertEquals("0897654321", check.get().getMobilePhone());
                assertEquals(gender, check.get().getGender());
                assertEquals(nameTitle, check.get().getNameTitle());
                assertEquals(place, check.get().getPlace());
                assertEquals(causeofDeath, check.get().getCauseofDeath());
        }

     // MobilePhone กรณีที่ 2 น้อยกว่า 10 ตัว
            @Test
            void B5905836_testMobilePhoneSizelessthan10() {
                Gender gender = genderRepository.findById(1);
                NameTitle nameTitle = nameTitleRepository.findById(1);
                Province province = provinceRepository.findById(1);
                Place place = placeRepository.findById(1);
                CauseofDeath causeofDeath = causeofDeathRepository.findById(1);
                
                RegisterDeaths r = new RegisterDeaths();
                Date Date = new Date();
                r.setFirstName("กดกดกด");
                r.setLastName("ดกดกดก");
                r.setAge(63);
                r.setBorn(Date);
                r.setDeath(Date);
                r.setAddressDetail("Address");
                r.setMobilePhone("0897654");
                r.setGender(gender);
                r.setNameTitle(nameTitle);
                r.setPlace(place);
                r.setCauseofDeath(causeofDeath);
                r.setProvince(province);

                Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

                // result ต้องมี error 1 ค่าเท่านั้น
                assertEquals(1, result.size());

                // error message ตรงชนิด และถูก field
                ConstraintViolation<RegisterDeaths> v = result.iterator().next();
                assertEquals("must match \"\\d{10}\"", v.getMessage());
                assertEquals("mobilePhone", v.getPropertyPath().toString());
            }
     // MobilePhone กรณีที่ 3 มากกว่า 10 ตัว
             @Test
             void B5905836_testMobilePhoneSizelessthan11() {
                Gender gender = genderRepository.findById(1);
                NameTitle nameTitle = nameTitleRepository.findById(1);
                Province province = provinceRepository.findById(1);
                Place place = placeRepository.findById(1);
                CauseofDeath causeofDeath = causeofDeathRepository.findById(1);

                RegisterDeaths r = new RegisterDeaths();
                Date Date = new Date();
                r.setFirstName("กดกดกด");
                r.setLastName("ดกดกดก");
                r.setAge(63);
                r.setBorn(Date);
                r.setDeath(Date);
                r.setAddressDetail("Address");
                r.setMobilePhone("0897654321123");
                r.setGender(gender);
                r.setNameTitle(nameTitle);
                r.setPlace(place);
                r.setCauseofDeath(causeofDeath);
                r.setProvince(province);
                 Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

                 // result ต้องมี error 1 ค่าเท่านั้น
                 assertEquals(1, result.size());

                 // error message ตรงชนิด และถูก field
                 ConstraintViolation<RegisterDeaths> v = result.iterator().next();
                 assertEquals("must match \"\\d{10}\"", v.getMessage());
                 assertEquals("mobilePhone", v.getPropertyPath().toString());
             }

      // MobilePhone กรณีที่ 4
             @Test
             void B5905836_testMobilePhoneMustNotBeNull() {
                Gender gender = genderRepository.findById(1);
                NameTitle nameTitle = nameTitleRepository.findById(1);
                Province province = provinceRepository.findById(1);
                Place place = placeRepository.findById(1);
                CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


                 RegisterDeaths r = new RegisterDeaths();
                 Date Date = new Date();
                 r.setFirstName("กดกดกด");
                 r.setLastName("ดกดกดก");
                 r.setAge(63);
                 r.setBorn(Date);
                 r.setDeath(Date);
                 r.setAddressDetail("Address");
                 r.setMobilePhone(null);
                 r.setGender(gender);
                 r.setNameTitle(nameTitle);
                 r.setPlace(place);
                 r.setCauseofDeath(causeofDeath);
                 r.setProvince(province);

                 Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

                 // result ต้องมี error 1 ค่าเท่านั้น
                 assertEquals(1, result.size());

                 // error message ตรงชนิด และถูก field

                 
                 ConstraintViolation<RegisterDeaths> v = result.iterator().next();
                 assertEquals("must not be null", v.getMessage());
                 assertEquals("mobilePhone", v.getPropertyPath().toString());
             }
             @Test
            void B5905836_testPlacewithCorrect() {
                Gender gender = genderRepository.findById(1);
                NameTitle nameTitle = nameTitleRepository.findById(1);
                Province province = provinceRepository.findById(1);
                Place place = placeRepository.findById(1);
                CauseofDeath causeofDeath = causeofDeathRepository.findById(1);

                RegisterDeaths re = new RegisterDeaths();
                Date Date = new Date();
                re.setFirstName("กดกดกด");
                re.setLastName("ดกดกดก");
                re.setAge(63);
                re.setBorn(Date);
                re.setDeath(Date);
                re.setAddressDetail("Address");
                re.setMobilePhone("0897654321");
                re.setGender(gender);
                re.setNameTitle(nameTitle);
                re.setPlace(place);
                re.setCauseofDeath(causeofDeath);
                re.setProvince(province);

                registerDeathsRepository.save(re);

                    Optional<RegisterDeaths> check = registerDeathsRepository.findById(re.getRegisterDeathsId());
                    assertEquals("กดกดกด", check.get().getFirstName());
                    assertEquals("ดกดกดก", check.get().getLastName());
                    assertEquals(63, check.get().getAge());
                    assertEquals(Date, check.get().getBorn());
                    assertEquals(Date, check.get().getDeath());
                    assertEquals("Address", check.get().getAddressDetail());
                    assertEquals("0897654321", check.get().getMobilePhone());
                    assertEquals(gender, check.get().getGender());
                    assertEquals(nameTitle, check.get().getNameTitle());
                    assertEquals(place, check.get().getPlace());
                    assertEquals(causeofDeath, check.get().getCauseofDeath());
                    assertEquals(province, check.get().getProvince());
            }
            // @Test
            // void B5905836_testPlaceMustNotBeNull() {
            //     Gender gender = genderRepository.findById(1);
            //     NameTitle nameTitle = nameTitleRepository.findById(1);
            //     Province province = provinceRepository.findById(1);
            //     Place place = placeRepository.findById(1);
            //     CauseofDeath causeofDeath = causeofDeathRepository.findById(1);


            //     RegisterDeaths r = new RegisterDeaths();
            //     r.setFirstName("กดกดกด");
            //     r.setLastName("ดกดกดก");
            //     r.setAge(63);
            //     r.setBorn("11-01-2500");
            //     r.setDeath("11-01-2563");
            //     r.setAddressDetail("Address");
            //     r.setMobilePhone("0897654321123");
            //     r.setGender(gender);
            //     r.setNameTitle(nameTitle);
            //     r.setPlace(null);
            //     r.setCauseofDeath(causeofDeath);
            //     r.setProvince(province);

            //     Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

            //     // result ต้องมี error 1 ค่าเท่านั้น
            //     assertEquals(1, result.size());

            //     // error message ตรงชนิด และถูก field

                
            //     ConstraintViolation<RegisterDeaths> v = result.iterator().next();
            //     assertEquals("must not be null", v.getMessage());
            //     assertEquals("place", v.getPropertyPath().toString());
            // }
            // @Test
            // void B5905836_testCauseofDeathNotBeNull() {
            //     Gender gender = genderRepository.findById(1);
            //     NameTitle nameTitle = nameTitleRepository.findById(1);
            //     Province province = provinceRepository.findById(1);
            //     Place place = placeRepository.findById(1);
            //     CauseofDeath causeofDeath = causeofDeathRepository.findById(1);



            //     RegisterDeaths r = new RegisterDeaths();
            //     r.setFirstName("กดกดกด");
            //     r.setLastName("ดกดกดก");
            //     r.setAge(63);
            //     r.setBorn("11-01-2500");
            //     r.setDeath("11-01-2563");
            //     r.setAddressDetail("Address");
            //     r.setMobilePhone("0897654321123");
            //     r.setGender(gender);
            //     r.setNameTitle(nameTitle);
            //     r.setPlace(place);
            //     r.setCauseofDeath(null);
            //     r.setProvince(province);

            //     Set<ConstraintViolation<RegisterDeaths>> result = validator.validate(r);

            //     // result ต้องมี error 1 ค่าเท่านั้น
            //     assertEquals(1, result.size());

            //     // error message ตรงชนิด และถูก field

                
            //     ConstraintViolation<RegisterDeaths> v = result.iterator().next();
            //     assertEquals("must not be null", v.getMessage());
            //     assertEquals("causeofDeath", v.getPropertyPath().toString());
            // }
}