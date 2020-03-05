package com.okta.springbootvue.Registerpatient.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@Entity
@NoArgsConstructor 
public class NameTitle {
    @Id
    @SequenceGenerator(name="NAMETITLE_SEQ",sequenceName="NAMETITLE_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAMETITLE_SEQ")
    @Column(name="NAMETITLE_ID",unique = true, nullable = false)
    private @NonNull Long nameTitleId;
    private @NotNull String nametitle;

    public Long getNameTitleId() {
        return nameTitleId;
    }

    public void setNameTitleId(Long nameTitleId) {
        this.nameTitleId = nameTitleId;
    }

    public String getNametitle() {
        return nametitle;
    }

    public void setNametitle(String nametitle) {
        this.nametitle = nametitle;
    }
    public NameTitle(String nameTitle) {
        this.nametitle = nameTitle;
    }

}