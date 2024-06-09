package com.apt.tracker.apartmentmanager.service;

import com.apt.tracker.apartmentmanager.exception.ResourceNotFoundException;
import com.apt.tracker.apartmentmanager.model.Task;
import com.apt.tracker.apartmentmanager.model.User;
import com.apt.tracker.apartmentmanager.repository.TaskRepository;
import com.apt.tracker.apartmentmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task createOrUpdateTask(Task task) {
        Task savedTask = taskRepository.save(task);
        Optional<User> userOptional = userRepository.findById(task.getUserID());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setTaskId(savedTask.getTaskID());
            userRepository.save(user);
        }
        return savedTask;
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
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (!taskOptional.isPresent()) {
            throw new RuntimeException("Task not found with id " + taskId);
        }

        Task task = taskOptional.get();
        Integer userId = task.getUserID();

        // Remove task ID from the user
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setTaskId(null);
            userRepository.save(user);
        }

        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Long taskId, Task taskDetails) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskId);
        if (!existingTaskOptional.isPresent()) {
            throw new RuntimeException("Task not found with id " + taskId);
        }

        Task existingTask = existingTaskOptional.get();
        Integer originalUserId = existingTask.getUserID();
        Integer newUserId = taskDetails.getUserID();

        // Update task details
        existingTask.setTaskName(taskDetails.getTaskName());
        existingTask.setTaskDescription(taskDetails.getTaskDescription());
        existingTask.setUserID(taskDetails.getUserID());

        Task updatedTask = taskRepository.save(existingTask);

        if (!originalUserId.equals(newUserId)) {
            // Remove task ID from the original user
            Optional<User> originalUserOptional = userRepository.findById(originalUserId);
            if (originalUserOptional.isPresent()) {
                User originalUser = originalUserOptional.get();
                originalUser.setTaskId(null);
                userRepository.save(originalUser);
            }

            // Add task ID to the new user
            Optional<User> newUserOptional = userRepository.findById(newUserId);
            if (newUserOptional.isPresent()) {
                User newUser = newUserOptional.get();
                newUser.setTaskId(updatedTask.getTaskID());
                userRepository.save(newUser);
            }
        }

        return updatedTask;
    }
}
