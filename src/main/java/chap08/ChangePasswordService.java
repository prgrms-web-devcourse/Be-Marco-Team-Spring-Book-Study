package chap08;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class ChangePasswordService {

    private MemberDao memberDao;

    @Transactional
    public void changePassword(String email, String oldPwd, String newPwd) {
        Optional<Member> optionalMember = memberDao.selectByEmail(email);
        if (optionalMember.isEmpty())
            throw new MemberNotFoundException();

        Member member = optionalMember.get();

        member.changePassword(oldPwd, newPwd);

        memberDao.update(member);
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

}

