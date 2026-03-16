package org.example.taskmanagerbackend.repository;

import org.example.taskmanagerbackend.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByUserEmail(String email, Pageable pageable);

}
