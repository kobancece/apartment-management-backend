package com.apt.tracker.apartmentmanager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private static final List<String> logs = Collections.synchronizedList(new ArrayList<>());
    private static final Map<String, AtomicInteger> methodCallCount = new ConcurrentHashMap<>();

    @Pointcut("execution(* com.apt.tracker.apartmentmanager.service.*.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        methodCallCount.putIfAbsent(methodName, new AtomicInteger(0));
        methodCallCount.get(methodName).incrementAndGet();
        
        String logMessage = "Entering method: " + methodName;
        logs.add(logMessage);
        logger.info(logMessage);
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        String logMessage = "Exiting method: " + joinPoint.getSignature().getName() + " with result: " + result;
        logger.info(logMessage);
    }

    public static Map<String, AtomicInteger> getMethodCallCount() {
        return methodCallCount;
    }

    public static List<String> getLogs() {
        // Sadece "Entering method" loglarını döndür
        List<String> enteringLogs = new ArrayList<>();
        for (String log : logs) {
            if (log.startsWith("Entering method:")) {
                enteringLogs.add(log);
            }
        }
        return enteringLogs;
    }

    public static void clearLogs() {
        logs.clear();
    }
}
