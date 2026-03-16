package org.example.taskmanagerbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // no need of validation here we will do that in request class inside dto
//    @NotBlank(message="Title cannot bee empty")
//    @Size(min=3, max=100, message="Title length must be between 3 and  100 characters..!")
    private String title;

//    @Size(max=500, message="Description cannot exceed 500 characters..!")
    private String description;
    private boolean completed;

//    here the task table will be related to user table
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Task() {}

    public Task(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
