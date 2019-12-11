package com.cpe.backend.Referral.controller;

import com.cpe.backend.Referral.entity.ForwardTo;
import com.cpe.backend.Referral.repository.ForwardToRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ForwardToController {

    @Autowired
    private final ForwardToRepository forwardToRepository;

    public ForwardToController(ForwardToRepository forwardToRepository) {
        this.forwardToRepository = forwardToRepository;
    }

    @GetMapping("/forwardTo")
    public Collection<ForwardTo> forwardTos() {
        return forwardToRepository.findAll().stream().collect(Collectors.toList());
    }

}