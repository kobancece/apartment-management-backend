package com.apt.tracker.apartmentmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apt.tracker.apartmentmanager.model.Task;
import com.apt.tracker.apartmentmanager.service.TaskService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/apartment/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            Task createdTask = taskService.createOrUpdateTask(task);
            return ResponseEntity.status(201).body(Map.of(
                "code", 201,
                "message", "Success.",
                "taskId", createdTask.getTaskID()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                "code", 500,
                "message", e.getMessage()
            ));
        }
    }

    @PostMapping("/{taskID}/update")
    public ResponseEntity<?> updateTask(@PathVariable Long taskID, @RequestBody Task taskDetails) {
        try {
            Task updatedTask = taskService.updateTask(taskID, taskDetails);
            return ResponseEntity.ok(updatedTask);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                "code", 500,
                "message", e.getMessage()
            ));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        try {
            List<Task> tasks = taskService.getAllTasks();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                "code", 500,
                "message", "Error retrieving tasks."
            ));
        }
    }

    @GetMapping("/{taskID}")
    public ResponseEntity<?> getTaskById(@PathVariable Long taskID) {
        try {
            Task task = taskService.getTaskById(taskID);
            return ResponseEntity.ok(task);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of(
                "code", 404,
                "message", "Task not found."
            ));
        }
    }

    @DeleteMapping("/{taskID}/delete")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskID) {
        try {
            taskService.deleteTask(taskID);
            return ResponseEntity.status(201).body(Map.of(
                "code", 201,
                "message", "Success."
            ));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of(
                "code", 404,
                "message", "Task not found."
            ));
        }
    }
}
