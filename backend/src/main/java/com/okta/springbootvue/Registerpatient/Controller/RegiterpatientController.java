package com.okta.springbootvue.Regiterpatient.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.Regiterpatient.Repository.*;
import com.okta.springbootvue.Regiterpatient.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class RegiterpatientController {

    @Autowired
    private RegiterpatientRepository regiterpatientRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private NameTitleRepository nameTitleRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    public RegiterpatientController(RegiterpatientRepository regiterpatientRepository) {
        this.regiterpatientRepository = regiterpatientRepository;

    }

    @GetMapping("/regiterpatients")
    public Collection<Regiterpatient> regiterpatients() {
        return regiterpatientRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/regiterpatient/{idCardnumber}/{firstName}/{lastName}/{age}/{buddhist}/{addressDetail}/{fathername}/{mothername}/{mobilePhone}/{email}/{provinceId}/{nameTitileId}/{genderId}")
    public Regiterpatient newRegiterpatient(@PathVariable int idCardnumber,@PathVariable String firstName, @PathVariable String lastName,
           @PathVariable int age,@PathVariable int buddhist,@PathVariable String addressDetail,
           @PathVariable String fathername,@PathVariable String mothername,@PathVariable String mobilePhone,
            @PathVariable String email, @PathVariable long provinceId, @PathVariable long nameTitileId,
            @PathVariable long genderId, @PathVariable long birthdayId, @PathVariable long monthId) {
        Regiterpatient newRegiterpatient = new Regiterpatient();
        System.out.println("Name = " + firstName);
        
        Province p = provinceRepository.findById(provinceId);
        NameTitle n = nameTitleRepository.findById(nameTitileId);
        Gender g = genderRepository.findById(genderId);

        newRegiterpatient.setIdCardnumber(idCardnumber);
        newRegiterpatient.setFirstName(firstName);
        newRegiterpatient.setLastName(lastName);
        newRegiterpatient.setAge(age);
        newRegiterpatient.setBuddhist(buddhist);
        newRegiterpatient.setAddressDetail(addressDetail);
        newRegiterpatient.setFatherName(fathername);
        newRegiterpatient.setMotherName(mothername);       
        newRegiterpatient.setMobilePhone(mobilePhone);
        newRegiterpatient.setEmail(email);


        newRegiterpatient.setProvince(p);
        newRegiterpatient.setGender(g);
        newRegiterpatient.setNameTitle(n);

        return regiterpatientRepository.save(newRegiterpatient);
    }

}