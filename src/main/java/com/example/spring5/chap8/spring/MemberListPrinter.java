package com.example.spring5.chap8.spring;

import java.util.Collection;

public class MemberListPrinter {

    private MemberRepository memberRepository;
    private MemberPrinter printer;

    public MemberListPrinter(MemberRepository memberRepository, MemberPrinter printer) {
        this.memberRepository = memberRepository;
        this.printer = printer;
    }

    public void printAll() {
        Collection<Member> members = memberRepository.selectAll();
        members.forEach(m -> printer.print(m));
    }

}
