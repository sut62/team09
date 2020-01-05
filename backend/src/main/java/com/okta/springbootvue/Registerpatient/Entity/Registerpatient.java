package com.okta.springbootvue.Registerpatient.Entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="REGISTERPATIENT")
public class Registerpatient {
	@Id
	@SequenceGenerator(name="REGISTERPATIENT_seq",sequenceName="REGISTERPATIENT_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGISTERPATIENT_seq")
	@Column(name="REGISTERPATIENT_ID",unique = true, nullable = true)
    private @NonNull Long registerId;
	private @NonNull String firstName;
    private @NonNull String lastName;
    private @NonNull Integer age;
    private @NonNull Integer weight;
    private @NonNull Integer height;
    private @NonNull String addressDetail;
	private @NonNull String mobilePhone;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private Gender gender;  

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = NameTitle.class)
    @JoinColumn(name = "NAMETITLE_ID", insertable = true)
    private NameTitle nameTitle;    

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public NameTitle getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(NameTitle nameTitle) {
        this.nameTitle = nameTitle;
    }

}