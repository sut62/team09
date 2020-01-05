package com.okta.springbootvue.Registerpatient.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.Registerpatient.Repository.*;
import com.okta.springbootvue.Registerpatient.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class RegisterpatientController {

    @Autowired
    private RegisterpatientRepository registerpatientRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private NameTitleRepository nameTitleRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    public RegisterpatientController(RegisterpatientRepository registerpatientRepository) {
        this.registerpatientRepository = registerpatientRepository;

    }

    @GetMapping("/registerpatients")
    public Collection<Registerpatient> registerpatients() {
        return registerpatientRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/registerpatient/{firstName}/{lastName}/{age}/{weight}/{height}/{addressDetail}/{mobilePhone}/{provinceId}/{nameTitileId}/{genderId}")
    public Registerpatient newRegiterpatient(@PathVariable String firstName, @PathVariable String lastName,
           @PathVariable int age,@PathVariable int weight,@PathVariable int height,@PathVariable String addressDetail,@PathVariable String mobilePhone,
           @PathVariable long provinceId, @PathVariable long nameTitileId,
            @PathVariable long genderId) {
        Registerpatient newRegiterpatient = new Registerpatient();
        
        Province p = provinceRepository.findById(provinceId);
        NameTitle n = nameTitleRepository.findById(nameTitileId);
        Gender g = genderRepository.findById(genderId);

        newRegiterpatient.setFirstName(firstName);
        newRegiterpatient.setLastName(lastName);
        newRegiterpatient.setAge(age);
        newRegiterpatient.setWeight(weight);
        newRegiterpatient.setHeight(height);
        newRegiterpatient.setAddressDetail(addressDetail);    
        newRegiterpatient.setMobilePhone(mobilePhone);

        newRegiterpatient.setProvince(p);
        newRegiterpatient.setGender(g);
        newRegiterpatient.setNameTitle(n);

        return registerpatientRepository.save(newRegiterpatient);
    }
    
}