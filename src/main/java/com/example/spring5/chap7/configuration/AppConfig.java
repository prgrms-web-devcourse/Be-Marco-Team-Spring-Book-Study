package com.example.spring5.chap7.configuration;

import com.example.spring5.chap7.aspect.ExeTimeAspect;
import com.example.spring5.chap7.calculator.Calculator;
import com.example.spring5.chap7.calculator.RecCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }

    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }

}