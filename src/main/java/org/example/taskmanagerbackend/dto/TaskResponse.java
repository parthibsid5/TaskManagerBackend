package org.example.taskmanagerbackend.dto;

public class TaskResponse {
    private Long id;
    private String title;
    private boolean completed;

    public TaskResponse(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
