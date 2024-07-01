package com.apt.tracker.apartmentmanager.service;

import com.apt.tracker.apartmentmanager.aspect.ExceptionHandlingAspect;
import com.apt.tracker.apartmentmanager.aspect.LoggingAspect;
import com.apt.tracker.apartmentmanager.aspect.PerformanceMonitoringAspect;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    public Map<String, String> getStatistics() {
        Map<String, String> statistics = new HashMap<>();

        // Exception handling statistics
        statistics.put("ExceptionCount", String.valueOf(ExceptionHandlingAspect.getExceptionCount()));
        statistics.put("LastExceptionMessage", ExceptionHandlingAspect.getLastExceptionMessage());

        // Logging statistics
        LoggingAspect.getMethodCallCount().forEach((method, count) ->
            statistics.put("MethodCallCount_" + method, String.valueOf(count.get()))
        );

        // Performance monitoring statistics
        PerformanceMonitoringAspect.getMethodExecutionTime().forEach((method, time) -> {
            int count = PerformanceMonitoringAspect.getMethodExecutionCount().get(method).get();
            long avgTime = time.get() / count;
            statistics.put("AvgExecutionTime_" + method, String.valueOf(avgTime));
        });

        return statistics;
    }

    public List<String> getLogs() {
        return LoggingAspect.getLogs();
    }
}
