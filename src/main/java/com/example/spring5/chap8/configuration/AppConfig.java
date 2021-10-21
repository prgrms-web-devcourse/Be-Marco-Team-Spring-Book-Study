package com.example.spring5.chap8.configuration;


import com.example.spring5.chap8.spring.ChangePasswordService;
import com.example.spring5.chap8.spring.MemberInfoPrinter;
import com.example.spring5.chap8.spring.MemberListPrinter;
import com.example.spring5.chap8.spring.MemberPrinter;
import com.example.spring5.chap8.spring.MemberRegisterService;
import com.example.spring5.chap8.spring.MemberRepository;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        System.out.println("start");
        var dataSource = DataSourceBuilder.create()
            .url("jdbc:mysql://localhost/spring_study")
            .username("root").password("root1234!").type(HikariDataSource.class).build();
        dataSource.setMinimumIdle(2);
        dataSource.setMaximumPoolSize(10);
        return dataSource;
    }


//    @Bean(destroyMethod = "close")
//    public DataSource dataSource() {
//        DataSource ds = new DataSource();
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
//        ds.setUsername("spring5");
//        ds.setPassword("spring5");
//        ds.setInitialSize(2);
//        ds.setMaxActive(10);
//        return ds;
//    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepository(dataSource());
    }

    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService(memberRepository());
    }

    @Bean
    public ChangePasswordService changePwdSvc() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberRepository(memberRepository());
        return pwdSvc;
    }

    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }

    @Bean
    public MemberListPrinter listPrinter() {
        return new MemberListPrinter(memberRepository(), memberPrinter());
    }

    @Bean
    public MemberInfoPrinter infoPrinter() {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setMemberRepository(memberRepository());
        infoPrinter.setPrinter(memberPrinter());
        return infoPrinter;
    }
}