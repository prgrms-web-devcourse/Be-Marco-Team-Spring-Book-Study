package chap08.Main;

import chap08.ChangePasswordService;
import chap08.MemberNotFoundException;
import chap08.WrongIdPasswordException;
import chap08.config.DbConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForCPS {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DbConfig.class);
        ChangePasswordService changePwdSvc = applicationContext.getBean("changePwdSvc", ChangePasswordService.class);
        try  {
            changePwdSvc.changePassword("madvirus@madvirus.com", "1234", "1111");
            System.out.println("암호를 변경했습니다.");
        } catch (MemberNotFoundException e) {
            System.out.println("회원 데이터가 존재하지 않습니다.");
        } catch (WrongIdPasswordException e) {
            System.out.println("암호가 올바르지 않습니다.");
        }

        applicationContext.close();
    }
}
