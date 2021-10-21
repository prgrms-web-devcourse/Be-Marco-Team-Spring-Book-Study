package com.example.spring5.chap7.aspect;

import java.text.MessageFormat;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
// @Order(1)
public class ExeTimeAspect {

    @Pointcut("execution(public * com.example.spring5.chap7.calculator..*(..))")
    private void publicTarget() {
    }

    @Around("publicTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
                joinPoint.getTarget().getClass().getSimpleName(),
                sig.getName(), Arrays.toString(joinPoint.getArgs()),
                (finish - start));
            System.out.println(
                MessageFormat.format("args: {0}", Arrays.toString(joinPoint.getArgs())));
        }
    }
}
