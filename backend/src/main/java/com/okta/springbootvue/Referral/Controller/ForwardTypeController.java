package com.cpe.backend.Referral.controller;

import com.cpe.backend.Referral.entity.ForwardType;
import com.cpe.backend.Referral.repository.ForwardTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ForwardTypeController {

    @Autowired
    private final ForwardTypeRepository forwardTypeRepository;

    public ForwardTypeController(ForwardTypeRepository forwardTypeRepository) {
        this.forwardTypeRepository = forwardTypeRepository;
    }

    @GetMapping("/forwardType")
    public Collection<ForwardType> forwardTypes() {
        return forwardTypeRepository.findAll().stream().collect(Collectors.toList());
    }

}