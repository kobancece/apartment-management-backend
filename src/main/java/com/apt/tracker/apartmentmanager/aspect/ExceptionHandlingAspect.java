package com.apt.tracker.apartmentmanager.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class ExceptionHandlingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAspect.class);
    private static final AtomicInteger exceptionCount = new AtomicInteger(0);
    private static String lastExceptionMessage = "";

    @Pointcut("execution(* com.apt.tracker.apartmentmanager.service.*.*(..))")
    public void serviceMethods() {}

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logException(Exception ex) {
        exceptionCount.incrementAndGet();
        lastExceptionMessage = ex.getMessage();
        logger.error("Exception occurred: " + ex.getMessage(), ex);
    }

    public static int getExceptionCount() {
        return exceptionCount.get();
    }

    public static String getLastExceptionMessage() {
        return lastExceptionMessage;
    }
}
