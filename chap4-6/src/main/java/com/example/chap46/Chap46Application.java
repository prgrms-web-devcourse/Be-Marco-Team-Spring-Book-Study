package com.example.chap46;

import com.example.chap46.config.AppCtx;
import com.example.chap46.spring.ChangePasswordService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class Chap46Application {

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        System.out.println(ctx.getBean(ChangePasswordService.class).getMemberDao());

//        SpringApplication.run(Chap46Application.class, args);
    }

}
