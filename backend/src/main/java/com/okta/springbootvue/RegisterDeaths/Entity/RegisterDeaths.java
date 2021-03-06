package com.okta.springbootvue.RegisterDeaths.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
// import java.util.Date;

import com.okta.springbootvue.Registerpatient.Entity.Gender;
import com.okta.springbootvue.Registerpatient.Entity.NameTitle;
import com.okta.springbootvue.Registerpatient.Entity.Province;

import org.springframework.format.annotation.DateTimeFormat;


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

    @Size(max=30,min=5)
    private @NotNull String firstName;

    @Size(max=30,min=5)
    private @NotNull String lastName;

    @Min(1)
    @Max(150)
    private @NotNull Integer age;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private @NonNull Date born;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private @NonNull Date death;

    @Size(max=50,min=5)
    private @NotNull String addressDetail;

    @Pattern(regexp = "\\d{10}")
	private @NotNull String mobilePhone;
    
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CauseofDeath.class)
    @JoinColumn(name = "CAUSEOFDEATH_ID", insertable = true)
    private CauseofDeath CauseofDeath;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Place.class)
    @JoinColumn(name = "PLACE_ID", insertable = true)
    private Place Place;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = NameTitle.class)
    @JoinColumn(name = "NAMETITLE", insertable = true)    
    private NameTitle nameTitle;


    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setPlace(Place place){
        this.Place = place;
    }
    public Place getPlace(){
        return Place;
    }
    public void setCauseofDeath(CauseofDeath causeofDeath){
        this.CauseofDeath = causeofDeath;
    }
    public CauseofDeath getCauseofDeath(){
        return CauseofDeath;
    }
    public Long getRegisterDeathsId() {
        return registerdeathId;
    }
    public void setRegisterDeathsId(Long registerdeathId) {
        this.registerdeathId = registerdeathId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public NameTitle getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(NameTitle nameTitle) {
        this.nameTitle = nameTitle;
    }

}