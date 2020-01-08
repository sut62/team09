package com.okta.springbootvue.Query.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.Query.Repository.*;
import com.okta.springbootvue.Query.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

@CrossOrigin(origins = "http://localhost:8080") 
 
public class CongenitalDiseaseController {

    private final CongenitalDiseaseRepository congenitalDiseaseRepository;

    @Autowired

    public CongenitalDiseaseController(CongenitalDiseaseRepository congenitalDiseaseRepository) {
        this.congenitalDiseaseRepository = congenitalDiseaseRepository;
    }

    @GetMapping("/congenitalDisease") 
    public Collection<CongenitalDisease> congenitalDiseases() {   
        return congenitalDiseaseRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/congenitalDisease/{congenitalDisease}")
    public CongenitalDisease addCongenitalDisease(@PathVariable String CongenitalDisease){
        CongenitalDisease c = new CongenitalDisease();
        c.setDuration(CongenitalDisease);
        congenitalDiseaseRepository.save(c);
        return c;
    }
}