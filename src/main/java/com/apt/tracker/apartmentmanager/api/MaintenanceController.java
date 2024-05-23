package com.apt.tracker.apartmentmanager.api;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apt.tracker.apartmentmanager.model.Maintenance;
import com.apt.tracker.apartmentmanager.service.MaintenanceService;

import java.util.Map;

@RestController
@RequestMapping("/apartment/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping("/setSchedule")
    public ResponseEntity<?> setMaintenanceSchedule(@RequestBody Maintenance schedule) {
        try {
            return ResponseEntity.status(201).body(Map.of("code", 201, "message", "Success."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("code", 500, "message", e.getMessage()));
        }
    }


    @PostMapping("/setPrioritize")
    public ResponseEntity<?> setMaintenancePriority(@RequestBody Maintenance maintenance) {
        try {
            maintenanceService.setMaintenancePriority(maintenance);
            return ResponseEntity.status(201).body(Map.of("code", 201, "message", "Success."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("code", 500, "message", e.getMessage()));
        }
    }

    @PostMapping("/{taskID}/setStatus")
    public ResponseEntity<?> setMaintenanceStatus(@PathVariable String taskID, @RequestBody Maintenance maintenance) {
        try {
            maintenance.setTaskID(taskID);
            maintenanceService.updateMaintenanceStatus(maintenance);
            return ResponseEntity.status(201).body(Map.of("code", 201, "message", "Success."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("code", 500, "message", e.getMessage()));
        }
    }

    @PostMapping("/{taskID}/update")
    public ResponseEntity<?> updateMaintenanceTask(@PathVariable String taskID, @RequestBody Maintenance maintenance) {
        try {
            maintenance.setTaskID(taskID);
            maintenanceService.updateMaintenanceTask(maintenance);
            return ResponseEntity.status(201).body(Map.of("code", 201, "message", "Success."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("code", 500, "message", e.getMessage()));
        }
    }
}
