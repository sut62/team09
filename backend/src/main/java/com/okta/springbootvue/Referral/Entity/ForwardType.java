package com.cpe.springbootvue.Referral.Entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor 
public class ForwardType {
    @Id
    @SequenceGenerator(name="FORWARDTYPE_SEQ",sequenceName="FORWARDTYPE_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FORWARDTYPE_SEQ")
    @Column(name="FORWARDTYPE_ID",unique = true, nullable = false)
    private @NonNull Long forwardTypeId;
    private @NonNull String forwardType;

    public Long getForwardTypeId() {
        return forwardTypeId;
    }

    public void setForwardTypeId(Long forwardTypeId) {
        this.forwardTypeId = forwardTypeId;
    }

    public String getForwardType() {
        return forwardType;
    }

    public void setForwardType(String forwardType) {
        this.forwardType = forwardType;
    }

}