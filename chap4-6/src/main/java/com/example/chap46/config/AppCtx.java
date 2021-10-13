package com.example.chap46.config;

import com.example.chap46.spring.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(basePackages = {"com.example.chap46.spring"})
//@ComponentScan(basePackages = {"com.example.chap46.spring", "com.example.chap46.spring2"},
//	excludeFilters = {
//		@Filter(type = FilterType.ANNOTATION, classes = ManualBean.class )
//	})
public class AppCtx {

//	@Bean
//	public MemberRepository memberRepository() {
//		return new MemberRepository();
//	}

	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
