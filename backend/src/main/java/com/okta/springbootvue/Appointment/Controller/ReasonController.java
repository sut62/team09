package com.okta.springbootvue.Appointment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.Appointment.Entity.*;
import com.okta.springbootvue.Appointment.Repository.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ReasonController {

    @Autowired
    private ReasonRepository reasonRepository;
    
    public ReasonController(ReasonRepository reasonRepository) {
        this.reasonRepository = reasonRepository;
    }

    @GetMapping("/reason")
    public Collection<Reason> Reasons() {
        return reasonRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/reason/{reasonID}")
    public Optional<Reason> Reason(@PathVariable Long reasonID) {
        return reasonRepository.findById(reasonID);

    }

    @GetMapping("/Reason/{name}")
    public Reason addReason(@PathVariable String name) {
        Reason r = new Reason();
        r.setReason(name);
        return reasonRepository.save(r);
    }
}