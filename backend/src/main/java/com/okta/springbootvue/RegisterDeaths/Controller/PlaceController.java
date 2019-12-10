package com.okta.springbootvue.RegisterDeaths.Controller;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.okta.springbootvue.RegisterDeaths.Repository.*;
import com.okta.springbootvue.RegisterDeaths.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class PlaceController {

    @Autowired private PlaceRepository placeRepository;

    @Autowired
    public PlaceController(PlaceRepository placeRepository){
        this.placeRepository = placeRepository;
    }

    @GetMapping("/Place")
    public  Collection<Place> Place(){
        return placeRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/Place/{placeID}")
    public  Optional<Place> Place(@PathVariable Long placeID){
        return placeRepository.findById(placeID);
    }

}