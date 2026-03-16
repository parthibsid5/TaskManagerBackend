package org.example.taskmanagerbackend.repository;

import org.example.taskmanagerbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface   UserRepository extends JpaRepository<User,Long> {
//    spring internally does sql query here like from users where email="####"

     Optional<User> findByEmail(String email);
}
