package com.example.chap46.spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberListPrinter {

	private MemberRepository memberRepository;
	private MemberPrinter printer;

	public MemberListPrinter() {
	}
	
	public MemberListPrinter(MemberRepository memberRepository, MemberPrinter printer) {
		this.memberRepository = memberRepository;
		this.printer = printer;
	}

	public void printAll() {
		Collection<Member> members = memberRepository.selectAll();
		members.forEach(m -> printer.print(m));
	}

	@Autowired
	public void setMemberDao(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Autowired
//	@Qualifier("summaryPrinter")
	public void setMemberPrinter(MemberSummaryPrinter printer) {
		this.printer = printer;
	}
}
