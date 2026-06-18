package com.teamtetra.todoapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamtetra.todoapp.entity.Subtask;

@Repository
public interface SubtaskRepo extends JpaRepository<Subtask, Long> {
    Optional<Subtask> findByTitle(String credential);
}
