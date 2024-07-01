package com.apt.tracker.apartmentmanager.api;

import com.apt.tracker.apartmentmanager.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apartment/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public Map<String, String> getStatistics() {
        return statisticsService.getStatistics();
    }

    @GetMapping("/logs")
    public List<String> getLogs() {
        return statisticsService.getLogs();
    }
}
