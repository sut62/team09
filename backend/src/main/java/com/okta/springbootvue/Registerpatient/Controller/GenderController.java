package com.okta.springbootvue.Registerpatient.Controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.Registerpatient.Repository.*;
import com.okta.springbootvue.Registerpatient.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
 @CrossOrigin(origins = "http://localhost:8080")
public class GenderController {

    @Autowired private GenderRepository genderRepository;

    @Autowired
    public GenderController(GenderRepository genderRepository){
        this.genderRepository = genderRepository;
    }

    @GetMapping("/Gender")
    public  Collection<Gender> Gender(){
        return genderRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/Gender/{genderID}")
    public  Optional<Gender> Gender(@PathVariable Long genderID){
        return genderRepository.findById(genderID);
    }

}