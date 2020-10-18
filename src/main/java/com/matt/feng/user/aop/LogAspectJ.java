package com.matt.feng.user.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Configuration
@EnableAspectJAutoProxy
@Component
@Aspect
@Slf4j
public class LogAspectJ {

    @Pointcut("execution(public * com.matt.feng.user..controller..*(..)) || " +
            "execution(public * com.matt.feng.user..service..*(..)) ")
    public void publicControllerAndServiceMethod() {
    }

    @Before("publicControllerAndServiceMethod()")
    public void beforePublicMethod(JoinPoint joinPoint) {
        log.info("Entering into : {}", joinPoint.getSignature().getName());
    }

    @After("publicControllerAndServiceMethod()")
    public void afterPublicMethod(JoinPoint joinPoint) {
        log.info("Exiting from : {}", joinPoint.getSignature().getName());
    }

}
