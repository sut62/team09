package com.okta.springbootvue.Diagnose.entity;

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


@Data
@Entity
@NoArgsConstructor
@Table(name="Query")
public class Query {

    @Id
    @SequenceGenerator(name="Query_seq",sequenceName="Query_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Query_seq")  
    @Column(name = "Query_ID", unique = true, nullable = true)
    private @NonNull Long queryId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Symptom.class)
    @JoinColumn(name = "Symptom_ID", insertable = true)
    private Symptom symptom;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Member.class)
    @JoinColumn(name = "member_ID", insertable = true)
    private Member member;
}
