package com.okta.springbootvue.Appointment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.Appointment.Entity.*;
import com.okta.springbootvue.Appointment.Repository.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ReasonController {

    @Autowired
    private final ReasonRepository reasonRepository;
    
    public ReasonController(ReasonRepository reasonRepository) {
        this.reasonRepository = reasonRepository;
    }

    @GetMapping("/reason")
    public Collection<Reason> Reasons() {
        return reasonRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/Reason/{name}")
    public Reason addReason(@PathVariable String name) {
        Reason r = new Reason();
        r.setReasonName(name);
        return reasonRepository.save(r);

    }
}