package com.apt.tracker.apartmentmanager.service;

import com.apt.tracker.apartmentmanager.model.Task;
import com.apt.tracker.apartmentmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createOrUpdateTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(Long taskID) {
        Optional<Task> task = taskRepository.findById(taskID);
        return task.orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksForTechnicians() {
        return taskRepository.findTasksAssignedToTechnicians();
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
