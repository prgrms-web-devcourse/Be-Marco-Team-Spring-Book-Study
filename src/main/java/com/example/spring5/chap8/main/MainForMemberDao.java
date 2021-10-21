package com.example.spring5.chap8.main;

import com.example.spring5.chap8.configuration.AppConfig;
import com.example.spring5.chap8.spring.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForMemberDao {
	private static MemberRepository memberRepository;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig.class);

		memberRepository = ctx.getBean(MemberRepository.class);

		selectAll();
		updateMember();
		insertMember();

		ctx.close();
	}

	private static void selectAll() {
		System.out.println("----- selectAll");
		int total = memberRepository.count();
		System.out.println("전체 데이터: " + total);
		List<Member> members = memberRepository.selectAll();
		for (Member m : members) {
			System.out.println(m.getId() + ":" + m.getEmail() + ":" + m.getName());
		}
	}

	private static void updateMember() {
		System.out.println("----- updateMember");
		Member member = memberRepository.selectByEmail("test@gmail.com");
		String oldPw = member.getPassword();
		String newPw = Double.toHexString(Math.random());
		member.changePassword(oldPw, newPw);

		memberRepository.update(member);
		System.out.println("암호 변경: " + oldPw + " > " + newPw);
	}

	private static DateTimeFormatter formatter = 
			DateTimeFormatter.ofPattern("MMddHHmmss");

	private static void insertMember() {
		System.out.println("----- insertMember");

		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member(prefix + "@test.com", 
				prefix, prefix, LocalDateTime.now());
		memberRepository.insert(member);
		System.out.println(member.getId() + " 데이터 추가");
	}

}
