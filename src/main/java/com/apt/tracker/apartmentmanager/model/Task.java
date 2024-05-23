package com.apt.tracker.apartmentmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskID", nullable = false)
    private Long taskID;

    @Column(name = "taskName", nullable = false)
    private String taskName;

    @Column(name = "userID", nullable = false)
    private Integer userID;

    @Column(name = "taskDescription", nullable = false)
    private String taskDescription;

    @Column(name = "assignedName", nullable = false)
    private String assignedName;

    public Task() {
    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getAssignedName() {
        return assignedName;
    }

    public void setAssignedName(String assignedName) {
        this.assignedName = assignedName;
    }
}
