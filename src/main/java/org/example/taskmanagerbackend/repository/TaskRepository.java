package org.example.taskmanagerbackend.repository;

import org.example.taskmanagerbackend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
