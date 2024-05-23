package com.apt.tracker.apartmentmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "maintenance")
public class Maintenance {

    @Id
    private String taskID;

    @Column(nullable = false)
    private String taskName;

    private String taskDescription;

    @Column(nullable = false)
    private String taskStatus = "Pending";

    @Column(nullable = false)
    private String taskPriority = "Medium";

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

}
