package com.example.spring5.chap8.spring;

import java.time.LocalDateTime;

public class MemberRegisterService {

    private MemberRepository memberRepository;

    public MemberRegisterService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long regist(RegisterRequest req) {
        Member member = memberRepository.selectByEmail(req.getEmail());
        if (member != null) {
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }
        Member newMember = new Member(
            req.getEmail(), req.getPassword(), req.getName(),
            LocalDateTime.now());
        memberRepository.insert(newMember);
        return newMember.getId();
    }
}
