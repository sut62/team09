package com.okta.springbootvue.Diagnose.controller;

import com.okta.springbootvue.Diagnose.entity.Member;
import com.okta.springbootvue.Diagnose.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MemberController {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/member")
    public Collection<Member> members() {
        return memberRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/member/{id}")
    public Optional<Member> members(@PathVariable Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member;
    }

}