package com.okta.springbootvue.Query.Entity;

import lombok.*;
import javax.persistence.*;

@Data 
@Entity 
@NoArgsConstructor  
@Table(name="DURATION") 

public class Duration {

	@Id
	@SequenceGenerator(name="Duration_seq",sequenceName="Duration_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Duration_seq")
	@Column(name="DURATION_ID",unique = true, nullable = false) 
  private @NonNull Long durationId;
  
  private @NonNull String Duration;

    public Long getDurationId() {  
        return durationId;
    }

    public void setDurationId(Long DurationId) {  
        this.durationId = DurationId;
    }

    public String getDuration() { 
        return Duration;
    }

    public void setDuration(String Duration) { 
        this.Duration = Duration;
    }


}
