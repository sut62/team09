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
@Table(name="REASON")
public class Reason {
	@Id
	@SequenceGenerator(name="REASON_seq",sequenceName="REASON_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REASON_seq")
	@Column(name="REASON_ID",unique = true, nullable = true)
    private @NonNull Long reasonId;
    
    private @NonNull String reason;

    public Long getReasonId() {
        return reasonId;
    }
    public void setReasonId(Long reasonId) {
        this.reasonId = reasonId;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
}