package com.cpe.backend.Referral.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cpe.backend.Referral.entity.Referral;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="REFERRAL")
public class Referral {

    @Id
    @SequenceGenerator(name="referral_seq",sequenceName="referral_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="referral_seq")
    @Column(name = "REFERRAL_ID", unique = true, nullable = true)
    private @NonNull Long id;
    
    private @NonNull Date referralDate;


    @Column(name="NOTE")
    private @NonNull String note;



    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ForwardTo.class)
    @JoinColumn(name = "ForwardTo_ID", insertable = true)
    private ForwardTo forwardTo;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ForwardType.class)
    @JoinColumn(name = "ForwardType_ID", insertable = true)
    private ForwardType forwardType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Deliver.class)
    @JoinColumn(name = "Deliver_ID", insertable = true)
    private Deliver deliver;

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "Diagnose_ID")
    private Diagnose diagnose;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(Date referralDate) {
        this.referralDate = referralDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ForwardTo getForwardTo() {
        return forwardTo;
    }

    public void setForwardTo(ForwardTo forwardTo) {
        this.forwardTo = forwardTo;
    }

    public ForwardType getForwardType() {
        return forwardType;
    }

    public void setForwardType(ForwardType forwardType) {
        this.forwardType = forwardType;
    }

    public Deliver getDeliver() {
        return deliver;
    }

    public void setDeliver(Deliver deliver) {
        this.deliver = deliver;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }


}