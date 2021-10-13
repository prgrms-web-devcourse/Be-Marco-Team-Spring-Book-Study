package com.example.chap46.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

	@Autowired
	private MemberRepository memberRepository;

	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberRepository.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();

		member.changePassword(oldPwd, newPwd);

		memberRepository.update(member);
	}

	public void setMemberDao(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
}
