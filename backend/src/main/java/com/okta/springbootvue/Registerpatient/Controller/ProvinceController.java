package com.okta.springbootvue.Registerpatient.Controller;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import com.okta.springbootvue.Registerpatient.Repository.*;
import com.okta.springbootvue.Registerpatient.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ProvinceController {

    @Autowired private ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceController(ProvinceRepository provinceRepository){
        this.provinceRepository = provinceRepository;

    }

    @GetMapping("/Province")
    public  Collection<Province> Province(){
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/Province/{provinceID}")
    public  Optional<Province> Province(@PathVariable Long provinceID){
        return provinceRepository.findById(provinceID);
    }


}