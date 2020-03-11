package com.okta.springbootvue.RegisterDeaths.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@Entity
@NoArgsConstructor
@Table(name="PLACE")
public class Place {
    @Id
    @SequenceGenerator(name="PLACE_seq",sequenceName="PLACE_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PLACE_seq")
    @Column(name="PLACE_ID",unique = true, nullable = true)
    private @NotNull Long placeId;

    private @NotNull String place;


    public Long getPlaceId() {
        return placeId;
    }
    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    public Place (String place) {
        this.place = place;
}

}

