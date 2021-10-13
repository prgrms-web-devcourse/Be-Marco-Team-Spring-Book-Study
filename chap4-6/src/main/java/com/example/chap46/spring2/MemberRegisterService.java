package com.example.chap46.spring2;

import com.example.chap46.config.NoProduct;
import com.example.chap46.spring.*;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@NoProduct
@Service
public class MemberRegisterService {
	private MemberRepository memberRepository;

	public MemberRegisterService() {
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

	@Autowired
	public void setMemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
}
