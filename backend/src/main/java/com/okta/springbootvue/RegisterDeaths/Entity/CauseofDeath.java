package com.okta.springbootvue.RegisterDeaths.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;


@Getter
@Setter
@Data
@Entity
@NoArgsConstructor
@Table(name="CAUSEOFDEATH")
public class CauseofDeath {
    @Id
    @SequenceGenerator(name="CAUSEOFDEATH_seq",sequenceName="CAUSEOFDEATH_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CAUSEOFDEATH_seq")
    @Column(name="CAUSEOFDEATH_ID",unique = true, nullable = true)
    private @NonNull Long causeofdeathId;
    private @NonNull String causeofdeath;

    public Long getCauseofDeathId() {
        return causeofdeathId;
    }
    public void setCauseofDeathId(Long causeofdeathId) {
        this.causeofdeathId = causeofdeathId;
    }

    public String getCauseofDeath() {
        return causeofdeath;
    }
    public void setCauseofDeath(String causeofdeath) {
        this.causeofdeath = causeofdeath;
    }
}