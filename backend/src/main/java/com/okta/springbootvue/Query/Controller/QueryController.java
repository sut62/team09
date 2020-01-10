package com.okta.springbootvue.Query.Controller;

import com.okta.springbootvue.Query.Entity.*;
import com.okta.springbootvue.Query.Repository.*;
import com.okta.springbootvue.Registerpatient.Entity.Registerpatient;
import com.okta.springbootvue.Registerpatient.Repository.RegisterpatientRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController

@CrossOrigin(origins = "http://localhost:8080")
public class QueryController {

    @Autowired
    private DurationRepository durationRepository;
    @Autowired
    private QueryRepository queryRepository;
    @Autowired
    private CongenitalDiseaseRepository congenitalDiseaseRepository;
    @Autowired
    private RegisterpatientRepository registerpatientRepository;

    public QueryController(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @GetMapping("/querys")
    public Collection<Query> querys() {
        return queryRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/query/{temperature}/{pressureSYS}/{pressureDIA}/{symptom}/{durationId}/{congenitalDiseaseId}/{patientId}")
    public Query newQuery(@PathVariable long durationId, @PathVariable long congenitalDiseaseId,
            @PathVariable Float temperature, @PathVariable Integer pressureSYS, @PathVariable Integer pressureDIA,
            @PathVariable String symptom, @PathVariable long patientId) {

        Query newquery = new Query();

        Duration d = durationRepository.findById(durationId);
        CongenitalDisease c = congenitalDiseaseRepository.findById(congenitalDiseaseId);
        Registerpatient registerpatient = registerpatientRepository.findById(patientId);
        newquery.setFirstName(registerpatient.getFirstName());
        newquery.setLastName(registerpatient.getLastName());
        newquery.setCongenitalDisease(c);
        newquery.setDuration(d);
        newquery.setRegisterpatient(registerpatient);
        newquery.setTemperature(temperature);
        newquery.setPressureSYS(pressureSYS);
        newquery.setPressureDIA(pressureDIA);
        newquery.setSymptom(symptom);

        return queryRepository.save(newquery);
    }

}