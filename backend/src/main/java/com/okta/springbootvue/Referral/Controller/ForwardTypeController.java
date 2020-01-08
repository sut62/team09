package com.okta.springbootvue.Referral.Controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.Referral.Repository.*;
import com.okta.springbootvue.Referral.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ForwardTypeController {

    @Autowired private ForwardTypeRepository forwardTypeRepository;

    @Autowired
    public ForwardTypeController(ForwardTypeRepository forwardTypeRepository){
        this.forwardTypeRepository = forwardTypeRepository;

    }

    @GetMapping("/ForwardType")
    public  Collection<ForwardType> forwardType(){
        return forwardTypeRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/ForwardType/{forwardTypeID}")
    public  Optional<ForwardType> forwardType(@PathVariable Long forwardTypeID){
        return forwardTypeRepository.findById(forwardTypeID);
    }


}