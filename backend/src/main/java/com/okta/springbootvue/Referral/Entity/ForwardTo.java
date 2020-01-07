package com.cpe.springbootvue.Referral.Entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor 
public class ForwardTo {
    @Id
    @SequenceGenerator(name="FORWARDTO_SEQ",sequenceName="FORWARDTO_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FORWARDTO_SEQ")
    @Column(name="FORWARDTO_ID",unique = true, nullable = false)
    private @NonNull Long forwardToId;
    private @NonNull String forwardTo;

    public Long getForwardToId() {
        return forwardToId;
    }

    public void setForwardToId(Long forwardToId) {
        this.forwardToId = forwardToId;
    }

    public String getForwardTo() {
        return forwardTo;
    }

    public void setForwardTo(String forwardTo) {
        this.forwardTo = forwardTo;
    }

}