package com.cpe.springboot.Query.Entity;

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
  private @NonNull Long CongenitalDiseaseId;
  
  private @NonNull String CongenitalDisease;

    public Long getCongenitalDiseaseId() {  
        return CongenitalDiseaseId;
    }

    public void setCongenitalDiseaseId(Long CongenitalDiseaseId) {  
        this.CongenitalDiseaseId = CongenitalDiseaseId;
    }

    public String getCongenitalDisease() { 
        return CongenitalDisease;
    }

    public void setDuration(String CongenitalDisease) { 
        this.CongenitalDisease = CongenitalDisease;
    }


}
