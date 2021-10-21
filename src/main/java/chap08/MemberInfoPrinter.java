package chap08;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MemberInfoPrinter {

	private MemberDao memDao;
	private MemberPrinter printer;

	public void printMemberInfo(String email) {
		Optional<Member> optionalMember = memDao.selectByEmail(email);
		if (optionalMember.isEmpty()) {
			System.out.println("데이터 없음\n");
			return;
		}
		Member member = optionalMember.get();
		printer.print(member);
		System.out.println();
	}

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}

	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

}
