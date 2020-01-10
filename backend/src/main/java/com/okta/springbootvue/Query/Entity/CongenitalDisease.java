package com.okta.springbootvue.Query.Entity;

import lombok.*;
import javax.persistence.*;

@Data 
@Entity 
@NoArgsConstructor  
@Table(name="CONGENITALDISEASE") 

public class CongenitalDisease {

	@Id
	@SequenceGenerator(name="CongenitalDisease_seq",sequenceName="CongenitalDisease_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CongenitalDisease_seq")
	@Column(name="CONGENITALDISEASE_ID",unique = true, nullable = false) 
  private @NonNull Long congenitalDiseaseId;
  
  private @NonNull String CongenitalDisease;

    public String getCongenitalDisease() {
        return this.CongenitalDisease;
    }

    public void setCongenitalDisease(String CongenitalDisease) {
        this.CongenitalDisease = CongenitalDisease;
    }

    public Long getCongenitalDiseaseId() {  
        return congenitalDiseaseId;
    }

    public void setCongenitalDiseaseId(Long CongenitalDiseaseId) {  
        this.congenitalDiseaseId = CongenitalDiseaseId;
    }


    public void setDuration(String CongenitalDisease) { 
        this.CongenitalDisease = CongenitalDisease;
    }


}
