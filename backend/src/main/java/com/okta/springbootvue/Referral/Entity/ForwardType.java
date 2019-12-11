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
@Table(name="FORWARDTYPE")
public class ForwardType {
    @Id
    @SequenceGenerator(name="FORWARDTYPE_SEQ",sequenceName="FORWARDTYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FORWARDTYPE_SEQ")
    @Column(name="FORWARDTYPE_ID",unique = true, nullable = true)
    private @NonNull Long forwardTypeId;

    private @NonNull String forwardType;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "ForwardType"
    private Collection<Referral> referral;

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

    public Collection<Referral> getReferral() {
        return referral;
    }

    public void setReferral(Collection<Referral> referral) {
        this.referral = referral;
    }


}