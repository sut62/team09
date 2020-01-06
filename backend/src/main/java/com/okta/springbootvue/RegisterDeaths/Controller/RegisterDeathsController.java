package com.okta.springbootvue.RegisterDeaths.Controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import com.okta.springbootvue.Regiterpatient.Repository.*;
import com.okta.springbootvue.Regiterpatient.Entity.*;
import com.okta.springbootvue.RegisterDeaths.Repository.*;
import com.okta.springbootvue.RegisterDeaths.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class RegisterDeathsController {
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private CauseofDeathRepository causeofDeathRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private NameTitleRepository nameTitleRepository;



    @Autowired private RegisterDeathsRepository registerDeathsRepository;

    @Autowired
    public RegisterDeathsController(RegisterDeathsRepository registerDeathsRepository){
        this.registerDeathsRepository = registerDeathsRepository;
    }

    @GetMapping("/RegisterDeaths")
    public  Collection<RegisterDeaths> Place(){
        return registerDeathsRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/RegisterDeaths/{registerdeathID}")
    public  Optional<RegisterDeaths> RegisterDeaths(@PathVariable Long registerdeathID){
        return registerDeathsRepository.findById(registerdeathID);
    }
    @PostMapping("/RegisterDeaths{idCardnumber}/{firstName}/{lastName}/{age}/{buddhist}/{addressDetail}/{fathername}/{mothername}/{mobilePhone}/{email}/{provinceId}/{nameTitileId}/{genderId}/{CauseofDeath}/{Place}")
    public RegisterDeaths newRegisterDeaths(@PathVariable int idCardnumber,@PathVariable String firstName, @PathVariable String lastName,
    @PathVariable int age,@PathVariable int buddhist,@PathVariable String addressDetail,
    @PathVariable String fathername,@PathVariable String mothername,@PathVariable String mobilePhone,
     @PathVariable String email, @PathVariable long provinceId, @PathVariable long nameTitileId,
     @PathVariable long genderId, @PathVariable long birthdayId, @PathVariable long monthId,@PathVariable long causeofDeathId ,@PathVariable long placeId) {
        RegisterDeaths registerDeaths = new RegisterDeaths();
        System.out.println("Name = " + firstName);


        CauseofDeath c = causeofDeathRepository.findById(causeofDeathId);        
        Place p = placeRepository.findById(placeId);
        NameTitle n = nameTitleRepository.findById(nameTitileId);
        Gender g = genderRepository.findById(genderId);

        registerDeaths.setIdCardnumber(idCardnumber);
        registerDeaths.setFirstName(firstName);
        registerDeaths.setLastName(lastName);
        registerDeaths.setAge(age);
        registerDeaths.setBuddhist(buddhist);
        registerDeaths.setAddressDetail(addressDetail);
        registerDeaths.setFatherName(fathername);
        registerDeaths.setMotherName(mothername);       
        registerDeaths.setMobilePhone(mobilePhone);
        registerDeaths.setEmail(email);


        registerDeaths.setGender(g);
        registerDeaths.setNameTitle(n);

        registerDeaths.setCauseofDeath(c);
        registerDeaths.setPlace(p);
       

        return registerDeathsRepository.save(registerDeaths);
    }
}