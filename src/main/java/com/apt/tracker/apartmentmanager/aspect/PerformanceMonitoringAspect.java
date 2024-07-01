package com.apt.tracker.apartmentmanager.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class PerformanceMonitoringAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);
    private static final ConcurrentHashMap<String, AtomicLong> methodExecutionTime = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AtomicInteger> methodExecutionCount = new ConcurrentHashMap<>();

    @Pointcut("execution(* com.apt.tracker.apartmentmanager.service.*.*(..))")
    public void serviceMethods() {}

    @Around("serviceMethods()")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;

        String methodName = joinPoint.getSignature().getName();
        methodExecutionTime.putIfAbsent(methodName, new AtomicLong(0));
        methodExecutionTime.get(methodName).addAndGet(duration);
        methodExecutionCount.putIfAbsent(methodName, new AtomicInteger(0));
        methodExecutionCount.get(methodName).incrementAndGet();

        logger.info("Execution time of " + methodName + " :: " + duration + " ms");
        return returnValue;
    }

    public static ConcurrentHashMap<String, AtomicLong> getMethodExecutionTime() {
        return methodExecutionTime;
    }

    public static ConcurrentHashMap<String, AtomicInteger> getMethodExecutionCount() {
        return methodExecutionCount;
    }
}
