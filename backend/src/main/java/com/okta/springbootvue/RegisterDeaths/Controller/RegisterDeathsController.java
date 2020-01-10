package com.okta.springbootvue.RegisterDeaths.Controller;

import java.util.Collection;
import java.util.stream.Collectors;
import com.okta.springbootvue.Registerpatient.Repository.*;
import com.okta.springbootvue.Registerpatient.Entity.*;
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

    @GetMapping("/RegisterDeath")
    public  Collection<RegisterDeaths> RegisterDeath(){
        return registerDeathsRepository.findAll().stream().collect(Collectors.toList());
    }
    
    @PostMapping("/RegisterDeaths/{firstName}/{lastName}/{age}/{born}/{death}/{addressDetail}/{mobilePhone}/{provinceId}/{nameTitileId}/{genderId}/{causeofDeathId}/{placeId}")
    public RegisterDeaths newRegisterDeaths(@PathVariable String firstName, @PathVariable String lastName,
    @PathVariable int age,@PathVariable String born,@PathVariable String death,@PathVariable String addressDetail,@PathVariable String mobilePhone,@PathVariable long provinceId, @PathVariable long nameTitileId,
     @PathVariable long genderId,@PathVariable long causeofDeathId ,@PathVariable long placeId) {
        RegisterDeaths registerDeaths = new RegisterDeaths();
        System.out.println("Name = " + firstName);


        CauseofDeath c = causeofDeathRepository.findById(causeofDeathId);        
        Place p = placeRepository.findById(placeId);
        NameTitle n = nameTitleRepository.findById(nameTitileId);
        Gender g = genderRepository.findById(genderId);
        registerDeaths.setFirstName(firstName);
        registerDeaths.setLastName(lastName);
        registerDeaths.setAge(age);
        registerDeaths.setBorn(born);
        registerDeaths.setDeath(death);
        registerDeaths.setAddressDetail(addressDetail);    
        registerDeaths.setMobilePhone(mobilePhone);


        registerDeaths.setGender(g);
        registerDeaths.setNameTitle(n);
        registerDeaths.setCauseofDeath(c);
        registerDeaths.setPlace(p);
       

        return registerDeathsRepository.save(registerDeaths);
    }
}