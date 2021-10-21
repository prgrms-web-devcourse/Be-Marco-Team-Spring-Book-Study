package com.example.spring5.chap8.spring;

public class MemberInfoPrinter {

    private MemberRepository memberRepository;
    private MemberPrinter printer;

    public void printMemberInfo(String email) {
        Member member = memberRepository.selectByEmail(email);
        if (member == null) {
            System.out.println("데이터 없음\n");
            return;
        }
        printer.print(member);
        System.out.println();
    }

    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }

}
