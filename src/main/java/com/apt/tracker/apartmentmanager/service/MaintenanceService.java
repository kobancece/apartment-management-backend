package com.apt.tracker.apartmentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apt.tracker.apartmentmanager.model.Maintenance;
import com.apt.tracker.apartmentmanager.repository.MaintenanceRepository;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    public Maintenance setMaintenanceSchedule(Maintenance schedule) {
        Maintenance maintenance = maintenanceRepository.findById(schedule.getTaskID()).orElse(new Maintenance());
        maintenance.setTaskID(schedule.getTaskID());
        maintenance.setTaskName(schedule.getTaskName());
        maintenance.setTaskDescription(schedule.getTaskDescription() != null ? schedule.getTaskDescription() : "No description provided");
        maintenance.setTaskStatus(schedule.getTaskStatus() != null ? schedule.getTaskStatus() : "Scheduled");
        maintenance.setTaskPriority(schedule.getTaskPriority() != null ? schedule.getTaskPriority() : "Medium");
        return maintenanceRepository.save(maintenance);
    }
    

    public void setMaintenancePriority(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
    }

    public void updateMaintenanceStatus(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
    }

    public void updateMaintenanceTask(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
    }
}
