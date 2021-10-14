package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.FilterType;
import spring.*;

@Configuration
@ComponentScan(basePackages = {"spring"})
public class AppCtx {

	@Bean
	@Qualifier("hihihihihi")
	//빈 이름과 한정자가 같은걸로 쓰이지는 않는듯?
	//빈 이름과 주입 대상 필드명이 다르면 두 곳 모두 @Qualifier써서 한정자를 맞춰줘야한다.
	//@Qualifier 보다 빈 이름이 더 우선순위가 있는듯 하다.
	public MemberPrinter printer() {
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

	@Bean
	public MemberDao memberDao2() {
		return new MemberDao();
	}

	@Bean
	public Client client() {
		return new Client("maenguin");
	}
}
