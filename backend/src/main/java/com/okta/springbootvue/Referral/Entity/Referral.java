package com.cpe.springbootvue.Referral.Entity;

import javax.persistence.*;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
@Table(name="REFERRAL")
public class Referral {
	@Id
	@SequenceGenerator(name="REFERRAL_seq",sequenceName="REFERRAL_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REFERRAL_seq")
    @Column(name="REFERRAL_ID",unique = true, nullable = true)
	private @NonNull Long referralId;
    private @NonNull String note;
    private @NonNull String firstName;
    private @NonNull String a;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ForwardTo.class)
    @JoinColumn(name = "FORWARDTO_ID", insertable = true)
    private ForwardTo forwardTo;  

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Deliver.class)
    @JoinColumn(name = "DELIVER_ID", insertable = true)
    private Deliver deliver;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ForwardType.class)
    @JoinColumn(name = "FORWARDTYPE_ID", insertable = true)
    private ForwardType forwardType;

    public Long getReferralId() {
		return referralId;
	}

    public void setReferralId(Long referralId) {
		this.referralId = referralId;
	}
    public String getA() {
		return a;
	}

    public void setA(String a) {
        this.a = a;
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

    public Deliver getDeliver() {
        return deliver;
    }

    public void setDeliver(Deliver deliver) {
        this.deliver = deliver;
    }

    public ForwardType getForwardType() {
        return forwardType;
    }

    public void setForwardType(ForwardType forwardType) {
        this.forwardType = forwardType;
    }

}