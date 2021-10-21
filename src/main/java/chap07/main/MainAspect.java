package chap07.main;

import chap07.Calculator;
import chap07.config.AppCtx;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator calculator = applicationContext.getBean("calculator", Calculator.class);
        long factorial = calculator.factorial(5);
        System.out.println("cal.factorial(5) = " + factorial);
        System.out.println(calculator.getClass().getName());
        applicationContext.close();
    }
}

