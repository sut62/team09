package com.okta.springbootvue.Registerpatient.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor  
public class Gender {
    @Id
    @SequenceGenerator(name="GENDER_SEQ",sequenceName="GENDER_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GENDER_SEQ")
    @Column(name="GENDER_ID",unique = true, nullable = false)
    private Long genderId;
    private @NotNull String gender;

    public Long getGenderId() {
        return genderId;
    }

    public void setGenderId(Long genderId) {
        this.genderId = genderId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public Gender(String gender){
        this.gender = gender;
    }
}