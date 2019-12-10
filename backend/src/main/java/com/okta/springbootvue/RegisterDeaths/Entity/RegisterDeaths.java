package com.okta.springbootvue.RegisterDeaths.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.Date;

import com.okta.springbootvue.Regiterpatient.Entity.Regiterpatient;

@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name="REGISTERDEATHS")
public class RegisterDeaths {

	@Id
	@SequenceGenerator(name="REGISTERDEATHS_seq",sequenceName="REGISTERDEATHS_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGISTERDEATHS_seq")
	@Column(name="REGISTERDEATHS_ID",unique = true, nullable = true)
    private @NonNull Long registerdeathId;
    private @NonNull Date time;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CauseofDeath.class)
    @JoinColumn(name = "CAUSEOFDEATH_ID", insertable = true)
    private CauseofDeath CauseofDeath;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Place.class)
    @JoinColumn(name = "PLACE_ID", insertable = true)
    private Place Place;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Regiterpatient.class)
    @JoinColumn(name = "REGITERPATIENT_ID", insertable = true)
    private Regiterpatient Regiterpatient;


    public Long getRegisterDeathsId() {
        return registerdeathId;
    }
    public void setAppointmentId(Long registerdeathId) {
        this.registerdeathId = registerdeathId;
    }
}