package com.cpe.backend.Bill.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cpe.backend.Booking.entity.Booking;
import com.cpe.backend.DomitoryRental.entity.Owner;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
@Entity
@Data
@NoArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode

@Table(name="Save_Bill")
public class SaveBill {
    @Id
    @SequenceGenerator(name="Save_Bill_seq",sequenceName="Save_Bill_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Save_Bill_seq")
    @Column(name = "Save_Bill_ID", unique = true, nullable = true)
    private @NonNull Long savebillId;
    private float totalprice;
    // private String nameOwner;
    // private String nameMember;

    //@Column(name="Owner")
    private @NonNull Date date;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Owner.class)
    @JoinColumn(name = "OWNER_ID", insertable = true)
    private Owner owner;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Booking.class)
    @JoinColumn(name = "Booking_ID", insertable = true)
    private Booking bookig;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Wifiservice.class)
    @JoinColumn(name = "WIFISERVICE_ID", insertable = true)
    private Wifiservice wifiservice;


    public Long getSavebillId() {
        return savebillId;
    }


    public void setSavebillId(Long savebillId) {
        this.savebillId = savebillId;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Booking getBookig() {
        return bookig;
    }

    public void setBookig(Booking bookig) {
        this.bookig = bookig;
    }

    public Wifiservice getWifiservice() {
        return wifiservice;
    }

    public void setWifiservice(Wifiservice wifiservice) {
        this.wifiservice = wifiservice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    // public void setNameOwner(String name) {
    //     this.nameOwner = name;
        
    //   }
    //   public void setNameMember(String name) {
    //     this.nameMember = name;
        
    //   }
    

}