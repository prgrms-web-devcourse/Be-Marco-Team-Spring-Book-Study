package com.example.spring5.chap8.spring;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {

    private MemberRepository memberRepository;

    @Transactional
    public void changePassword(String email, String oldPwd, String newPwd) {
        Member member = memberRepository.selectByEmail(email);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        member.changePassword(oldPwd, newPwd);
        memberRepository.update(member);
    }

    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

}
