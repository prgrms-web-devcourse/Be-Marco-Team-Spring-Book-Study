package com.example.spring5.chap7.main;

import com.example.spring5.chap7.calculator.Calculator;
import com.example.spring5.chap7.calculator.RecCalculator;
import com.example.spring5.chap7.configuration.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(AppConfig.class);

        Calculator cal = ctx.getBean("calculator", Calculator.class);
//        RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
        long fiveFact = cal.factorial(5);
        System.out.println("cal.factorial(5) = " + fiveFact);
        System.out.println(cal.getClass().getName());
        ctx.close();
    }
}
