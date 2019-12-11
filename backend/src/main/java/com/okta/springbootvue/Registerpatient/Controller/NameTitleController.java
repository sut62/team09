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
public class NameTitleController {

    @Autowired private NameTitleRepository nameTitleRepository;

    @Autowired
    public NameTitleController(NameTitleRepository nameTitleRepository){
        this.nameTitleRepository = nameTitleRepository;

    }

    @GetMapping("/NameTitle")
    public  Collection<NameTitle> nameTitles(){
        return nameTitleRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/NameTitle/{nametitleID}")
    public  Optional<NameTitle> nameTitles(@PathVariable Long nametitleID){
        return nameTitleRepository.findById(nametitleID);
    }


}