package com.tj.edu.practice4.aop.aops;

import com.tj.edu.practice4.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {
    @Pointcut("execution(* com.tj.edu.practice4.aop.controller.RestApiController.updateUser(..)) ")
    private void cut1() {
    }

    @Before("cut1()")
    public void before(JoinPoint joinPoint) {
        System.out.println("updateUser 실행됨");
    }

    @AfterReturning("cut1()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("updateUser 종료됨");

    }

    @Around("cut1()")
    public Object around1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        return result;
    }

}
