package com.cpe.springboot.Query.Controller;

import com.cpe.springboot.Query.Entity.*;
//import com.cpe.backend.Register.Entity.*;
import com.cpe.springboot.Query.Repository.*;
//import com.cpe.backend.Register.Repository.*;

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
    private CongenitalDiseaseRepository  congenitalDiseaseRepository;
 

    public QueryController(QueryRepository queryRepository,
    CongenitalDiseaseRepository congenitalDiseaseRepository,
    DurationRepository durationRepository){
        this.queryRepository = queryRepository;
        this.congenitalDiseaseRepository = congenitalDiseaseRepository;
        this.durationRepository = durationRepository;
    }

    @GetMapping("/querys") 
    public  Collection<Query> Query(){  
        return queryRepository.findAll().stream().collect(Collectors.toList());  
    }

    @PostMapping("/query/durationId}/{congenitalDiseaseId}") 
    public Query newQuery(@PathVariable long durationId, @PathVariable long congenitalDiseaseId) {

       Duration d = durationRepository.findById(durationId);
       CongenitalDisease c = congenitalDiseaseRepository.findById(congenitalDiseaseId);
       
       Query query = new Query(); 

       query.setCongenitalDisease(c); 
       query.setDuration(d);
       
        
        return queryRepository.save(query); 
    }
    @GetMapping("/query/{id}") 
    public QueryRepository bookingByUser(@PathVariable Long id) {
       
        
        return queryRepository;
    }
  
}