package com.apt.tracker.apartmentmanager.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceMonitoringAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);

    @Pointcut("execution(* com.apt.tracker.apartmentmanager.service.*.*(..))")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        logger.info("Execution time of " + joinPoint.getSignature().getName() + " :: " + duration + " ms");
        return returnValue;
    }
}
