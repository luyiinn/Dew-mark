//package com.itheima.aop;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//@Slf4j
//public class TimeAspect {
//
//    @Around("execution(* com.itheima.service.*.*(..))") // 切入点表达式
//    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        //记录开始时间
//        long begin = System.currentTimeMillis();
//
//        //调用原始方法运行
//        Object result = joinPoint.proceed();
//
//        //记录结束时间
//        long end = System.currentTimeMillis();
//        log.info(joinPoint.getSignature()+"方法执行耗时：{}",end-begin);// getSignature() 拿到方法的签名
//
//        return result;
//    }
//}
