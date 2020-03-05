package com.okta.springbootvue.Registerpatient.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor 
public class Province {
    @Id
    @SequenceGenerator(name="PROVINCE_SEQ",sequenceName="PROVINCE_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROVINCE_SEQ")
    @Column(name="PROVINCE_ID",unique = true, nullable = false)
    private Long provinceId;
    private @NotNull String province;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Province (String province) {
        this.province = province;
}
}