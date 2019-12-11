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
@Table(name="FORWARDTO")
public class ForwardTo {
    @Id
    @SequenceGenerator(name="FORWARDTO_SEQ",sequenceName="FORWARDTO_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FORWARDTO_SEQ")
    @Column(name="FORWARDTO_ID",unique = true, nullable = true)
    private @NonNull Long forwardToId;

    private @NonNull String forwardTo;

    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "ForwardTo"
    private Collection<Referral> referral;

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

    public Collection<Referral> getReferral() {
        return referral;
    }

    public void setReferral(Collection<Referral> referral) {
        this.referral = referral;
    }


}