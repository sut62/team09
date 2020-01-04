package com.okta.springbootvue.Diagnose.controller;

import com.okta.springbootvue.Diagnose.entity.Query;
import com.okta.springbootvue.Diagnose.entity.Member;
import com.okta.springbootvue.Diagnose.repository.QueryRepository;
import com.okta.springbootvue.Diagnose.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class QueryController {

    @Autowired
    private final QueryRepository queryRepository;
    @Autowired
    private final MemberRepository memberRepository;

    public QueryController(QueryRepository queryRepository,MemberRepository memberRepository) {
        this.queryRepository = queryRepository;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/query")
    public Collection<Query> querys() {
        return queryRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/query/{id}")
    public Collection<Query> queryByUser(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return queryRepository.findByMember(member);
    }

}