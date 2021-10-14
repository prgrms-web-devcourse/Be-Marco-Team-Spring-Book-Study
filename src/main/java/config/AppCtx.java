package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.FilterType;
import spring.*;

@Configuration
@ComponentScan(basePackages = {"spring"},
//	excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "spring\\..*Dao") //정규표현식으로 필터링
//	excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "spring.*Dao")	//AspectJ 패턴으로 필터링
//	excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ManualBean.class) //Annotation으로 필터링
	excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MemberDao.class) //특정 타입과 그 하위 타입 필터링
)
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
}
