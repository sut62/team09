package com.cpe.backend.Referral.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.cpe.backend.Referral.entity.Deliver;
import com.cpe.backend.Referral.repository.DeliverRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DeliverController {

    @Autowired
    private final DeliverRepository deliverRepository;

    public DeliverController(DeliverRepository deliverRepository) {
        this.deliverRepository = deliverRepository;
    }

    @GetMapping("/deliver")
    public Collection<Deliver> delivers() {
        return deliverRepository.findAll().stream().collect(Collectors.toList());
    }

}