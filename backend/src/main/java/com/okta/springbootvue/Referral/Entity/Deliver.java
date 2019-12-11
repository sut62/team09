package com.cpe.backend.Referral.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="DELIVER")
public class Deliver {
    @Id
    @SequenceGenerator(name="DELIVER_SEQ",sequenceName="DELIVER_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DELIVER_SEQ")
    @Column(name="DELIVER_ID",unique = true, nullable = true)
    private @NonNull Long deliverId;

    private @NonNull String deliver;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "Deliver"
    private Collection<Referral> referral;

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

    public Collection<Referral> getReferral() {
        return referral;
    }

    public void setReferral(Collection<Referral> referral) {
        this.referral = referral;
    }


}