package com.mihaicraciun.kbn.kbn.services;

import java.util.Optional;

import com.mihaicraicun.kbn.kbn.controllers.exceptions.ProjectNotFoundException;
import com.mihaicraicun.kbn.kbn.model.forms.ProjectForm;

public interface ProjectService {
    Optional<Project> findById(String id);
    Optional<Project> findByUserAndName(User user, String name);
    Project create(ProjectForm projectForm, User user);
    void save(Project project);
    void deleteById(String projectId);
	void update(Project project, ProjectForm projectForm);
    Project getProjectByIdVisibleByCurrentUser(String id) throws ProjectNotFoundException;
    Project getProjectByIdEditableByCurrentUser(String id) throws ProjectNotFoundException;
}
