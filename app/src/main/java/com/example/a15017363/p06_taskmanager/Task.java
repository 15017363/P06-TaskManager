package com.example.a15017363.p06_taskmanager;

import java.io.Serializable;

/**
 * Created by 15017363 on 25/5/2017.
 */

public class Task implements Serializable {
    private int id;
    private String taskName;
    private String taskContent;

    public Task(int id, String taskName, String taskContent) {
        this.id = id;
        this.taskName = taskName;
        this.taskContent = taskContent;
    }

    public int getId() {
        return id;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }
}
