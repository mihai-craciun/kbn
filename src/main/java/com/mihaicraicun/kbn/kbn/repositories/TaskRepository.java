package com.mihaicraicun.kbn.kbn.repositories;

import java.util.Optional;

import com.mihaicraicun.kbn.kbn.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public Optional<Task> findById(String id);
    public void deleteById(String id);
}
