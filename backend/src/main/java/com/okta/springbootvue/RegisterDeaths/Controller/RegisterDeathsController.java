package com.okta.springbootvue.RegisterDeaths.Controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.RegisterDeaths.Repository.*;
import com.okta.springbootvue.RegisterDeaths.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class RegisterDeathsController {

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

}