package com.okta.springbootvue.Diagnose.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="QUERY")
public class Query {
    @Id
    @SequenceGenerator(name="QUERY_SEQ",sequenceName="QUERY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="QUERY_SEQ")
    @Column(name="QUERY_ID",unique = true, nullable = true)
    private @NonNull Long queryId;

    private @NonNull String name;

    public Long getQueryId() {
        return queryId;
    }

    public void setQueryId(Long queryId) {
        this.queryId = queryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}