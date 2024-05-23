package com.apt.tracker.apartmentmanager.repository;

import com.apt.tracker.apartmentmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.assignedName IS NOT NULL")
    List<Task> findTasksAssignedToTechnicians();
}
