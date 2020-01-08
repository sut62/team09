package com.okta.springbootvue.Appointment.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name="DEMEANOR")
public class Demeanor {
	@Id
	@SequenceGenerator(name="DEMEANOR_seq",sequenceName="DEMEANOR_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEMEANOR_seq")
	@Column(name="DEMEANOR_ID",unique = true, nullable = true)
    private @NonNull Long demeanorId;
    
    private @NonNull String demeanor;

    public Long getDemeanorId() {
        return demeanorId;
    }
    public void setDemeanorId(Long demeanorId) {
        this.demeanorId = demeanorId;
    }

    public String getDemeanor() {
        return demeanor;
    }
    public void setDemeanor(String demeanor) {
        this.demeanor = demeanor;
    }
}