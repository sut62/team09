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
public class CauseofDeathController {

    @Autowired private CauseofDeathRepository causeofDeathRepository;

    @Autowired
    public CauseofDeathController(CauseofDeathRepository causeofDeathRepository){
        this.causeofDeathRepository = causeofDeathRepository;
    }

    @GetMapping("/CauseofDeath")
    public  Collection<CauseofDeath> CauseofDeath(){
        return causeofDeathRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/CauseofDeath/{CauseofDeathID}")
    public  Optional<CauseofDeath> CauseofDeath(@PathVariable Long causeofdeathID){
        return causeofDeathRepository.findById(causeofdeathID);
    }

}