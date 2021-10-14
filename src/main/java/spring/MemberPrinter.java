package spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

public class MemberPrinter {

	@Autowired(required = false)
	private DateTimeFormatter dateTimeFormatter;

	//기본 생성자로 dateTimeFormatter 필드를 초기화 한다.
	public MemberPrinter() {
		this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	}

	public void print(Member member) {
		System.out.println("dateTimeFormatter = " + dateTimeFormatter);
		System.out.printf(
				"회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
				member.getId(), member.getEmail(),
				member.getName(), member.getRegisterDateTime());
	}

}
