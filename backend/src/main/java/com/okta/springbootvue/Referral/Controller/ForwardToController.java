package com.cpe.springbootvue.Referral.Controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cpe.springbootvue.Referral.Repository.*;
import com.cpe.springbootvue.Referral.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ForwardToController {

    @Autowired private ForwardToRepository forwardToRepository;

    @Autowired
    public ForwardToController(ForwardToRepository forwardToRepository){
        this.forwardToRepository = forwardToRepository;

    }

    @GetMapping("/ForwardTo")
    public  Collection<ForwardTo> forwardTo(){
        return forwardToRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/ForwardTo/{forwardToID}")
    public  Optional<ForwardTo> forwardTo(@PathVariable Long forwardToID){
        return forwardToRepository.findById(forwardToID);
    }


}