package com.okta.springbootvue.Diagnose.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="MEMBER")
public class Member {
	@Id
	@SequenceGenerator(name="MEMBER_seq",sequenceName="MEMBER_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEMBER_seq")
	@Column(name="MEMBER_ID",unique = true, nullable = true)
	private @NonNull Long memberId;
	private @NonNull String name;

	@OneToMany(fetch = FetchType.EAGER)

	private Collection<Diagnose> diagnose;

}
