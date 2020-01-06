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

import com.okta.springbootvue.Regiterpatient.Entity.Gender;
import com.okta.springbootvue.Regiterpatient.Entity.NameTitle;
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
    private @NonNull Integer idCardnumber;
	private @NonNull String firstName;
    private @NonNull String lastName;
    private @NonNull Integer age;
    private @NonNull Integer buddhist;
    private @NonNull String addressDetail;
    private @NonNull String fatherName;
	private @NonNull String motherName;
	private @NonNull String mobilePhone;
    private @NonNull String email;
    
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CauseofDeath.class)
    @JoinColumn(name = "CAUSEOFDEATH_ID", insertable = true)
    private CauseofDeath CauseofDeath;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Place.class)
    @JoinColumn(name = "PLACE_ID", insertable = true)
    private Place Place;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Regiterpatient.class)
    @JoinColumn(name = "REGITERPATIENT_ID", insertable = true)
    private Regiterpatient Regiterpatient;
    private Gender gender;
    private NameTitle nameTitle;

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
    public Integer getIdCardnumber() {
        return idCardnumber;
    }

    public void setIdCardnumber(Integer idCardnumber) {
        this.idCardnumber = idCardnumber;
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

    public Integer getBuddhist() {
        return buddhist;
    }

    public void setBuddhist(Integer buddhist) {
        this.buddhist = buddhist;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

	public void setRegiterpatient(Regiterpatient Regiterpatient) {
        this.Regiterpatient = Regiterpatient;
	}
    public Regiterpatient gRegiterpatient(){
        return Regiterpatient;
    }
}