package com.okta.springbootvue.Referral.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor 
public class Deliver {
    @Id
    @SequenceGenerator(name="DELIVER_SEQ",sequenceName="DELIVER_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DELIVER_SEQ")
    @Column(name="DELIVER_ID",unique = true, nullable = false)
    private @NotNull Long deliverId;
    private @NotNull String deliver;

    public Long getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Long deliverId) {
        this.deliverId = deliverId;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

}