package com.okta.springbootvue.Query.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.Query.Repository.*;
import com.okta.springbootvue.Query.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

@CrossOrigin(origins = "http://localhost:8080") 
 
public class DurationController {

    private final DurationRepository durationRepository;

    @Autowired

    public DurationController(DurationRepository durationRepository) {
        this.durationRepository = durationRepository;
    }

    @GetMapping("/duration") 
    public Collection<Duration> durations() {   
        return durationRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/duration/{duration}")
    public Duration addDuration(@PathVariable String Duration){
        Duration d = new Duration();
        d.setDuration(Duration);
        durationRepository.save(d);
        return d;
    }
}