package com.okta.springbootvue.Diagnose.controller;

import com.okta.springbootvue.Diagnose.entity.*;
import com.okta.springbootvue.Diagnose.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class QueryController {

    @Autowired
    private final QueryRepository queryRepository;

    public QueryController(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @GetMapping("/query")
    public Collection<Query> queryRepository() {
        return queryRepository.findAll().stream().collect(Collectors.toList());
    }

}