package com.mihaicraicun.kbn.kbn.repositories;

import java.util.List;
import java.util.Optional;

import com.mihaicraicun.kbn.kbn.model.Project;
import com.mihaicraicun.kbn.kbn.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findById(String id);
    void deleteById(String id);
    List<Project> findByOwner(User owner);
    Optional<Project> findByOwnerAndName(User owner, String name);
}
