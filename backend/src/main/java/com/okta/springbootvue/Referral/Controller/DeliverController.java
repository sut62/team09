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
public class DeliverController {

    @Autowired private DeliverRepository deliverRepository;

    @Autowired
    public DeliverController(DeliverRepository deliverRepository){
        this.deliverRepository = deliverRepository;

    }

    @GetMapping("/Deliver")
    public  Collection<Deliver> deliver(){
        return deliverRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/Deliver/{deliverID}")
    public  Optional<Deliver> deliver(@PathVariable Long deliverID){
        return deliverRepository.findById(deliverID);
    }


}