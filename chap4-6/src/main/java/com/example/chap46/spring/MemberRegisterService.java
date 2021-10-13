package com.example.chap46.spring;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService {
	@Autowired
	private MemberRepository memberRepository;

	public MemberRegisterService() {
	}
	
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