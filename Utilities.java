package com.cpe.backend.Utilities.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
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

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name="UTILITIES")
public class Utilities {

    @Id
    @SequenceGenerator(name="UTILITIES_SEQ",sequenceName="UTILITIES_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="UTILITIES_SEQ")
    @Column(name = "UTILITIES_ID", unique = true, nullable = true)
    private @NonNull Long utilitirsId;

    private @NonNull Date saveDate;

    private @NonNull Integer waterMeters;

    private @NonNull Float waterAmount;

    private @NonNull Integer electricMeters;

    private @NonNull Float electricAmount;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = WaterUnitRate.class)
    @JoinColumn(name = "WATER_UNIT_RATE_ID", insertable = true)
    private WaterUnitRate waterUnitRate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ElecUnitRate.class)
    @JoinColumn(name = "ELEC_UNIT_RATE_ID", insertable = true)
    private ElecUnitRate elecUnitRate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Booking.class)
    @JoinColumn(name = "BOOKING_ID", insertable = true)
    private Booking booking;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Owner.class)
    @JoinColumn(name = "OWNER_ID", insertable = true)
    private Owner owner;

    public Long getUtilitirsId() {
        return utilitirsId;
    }

    public void setUtilitirsId(Long utilitirsId) {
        this.utilitirsId = utilitirsId;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public Integer getWaterMeters() {
        return waterMeters;
    }

    public void setWaterMeters(Integer waterMeters) {
        this.waterMeters = waterMeters;
    }

    public Float getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(Float waterAmount) {
        this.waterAmount = waterAmount;
    }

    public Integer getElectricMeters() {
        return electricMeters;
    }

    public void setElectricMeters(Integer electricMeters) {
        this.electricMeters = electricMeters;
    }

    public Float getElectricAmount() {
        return electricAmount;
    }

    public void setElectricAmount(Float electricAmount) {
        this.electricAmount = electricAmount;
    }

    public WaterUnitRate getWaterUnitRate() {
        return waterUnitRate;
    }

    public void setWaterUnitRate(WaterUnitRate waterUnitRate) {
        this.waterUnitRate = waterUnitRate;
    }

    public ElecUnitRate getElecUnitRate() {
        return elecUnitRate;
    }

    public void setElecUnitRate(ElecUnitRate elecUnitRate) {
        this.elecUnitRate = elecUnitRate;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}