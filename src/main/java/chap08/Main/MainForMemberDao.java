package chap08.Main;

import chap08.config.DbConfig;
import chap08.Member;
import chap08.MemberDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class MainForMemberDao {

    private static MemberDao memberDao;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DbConfig.class);

        memberDao = applicationContext.getBean(MemberDao.class);

        selectAll();
        updateMember();
        insertMember();

        applicationContext.close();
    }

    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMddHHmmss");

    private static void insertMember() {
        System.out.println("-------insertMember");

        String prefix = formatter.format(LocalDateTime.now());
        Member member = new Member(prefix + "@test.com", prefix, prefix, LocalDateTime.now());
        memberDao.insert(member);
        System.out.println(member.getId() + " 데이터 추가");
    }

    private static void updateMember() {
        System.out.println("-------updateMember");
        Optional<Member> optionalMember = memberDao.selectByEmail("madvirus@madvirus.com");
        Member member = optionalMember.orElseThrow();

        String oldPw = member.getPassword();
        String newPw = Double.toHexString(Math.random());

        member.changePassword(oldPw, newPw);

        memberDao.update(member);
        System.out.println("암호 변경 : " + oldPw + " >> " + newPw);
    }

    private static void selectAll() {

        System.out.println("-------SelectAll");
        int total = memberDao.count();
        System.out.println("전체 데이터 : " + total);

        List<Member> members = memberDao.selectAll();

        for (Member member : members) {
            System.out.println(member.getId() + " : " + member.getEmail() + " : " + member.getName());
        }
    }
}
