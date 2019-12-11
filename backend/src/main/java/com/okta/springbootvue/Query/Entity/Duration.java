package com.cpe.springboot.Query.Entity;

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
  private @NonNull Long DurationId;
  
  private @NonNull String Duration;

    public Long getDurationId() {  
        return DurationId;
    }

    public void setDurationId(Long DurationId) {  
        this.DurationId = DurationId;
    }

    public String getDuration() { 
        return Duration;
    }

    public void setDuration(String Duration) { 
        this.Duration = Duration;
    }


}
