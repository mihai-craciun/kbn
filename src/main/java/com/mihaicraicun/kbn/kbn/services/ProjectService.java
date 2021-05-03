package com.mihaicraicun.kbn.kbn.services;

import java.util.Optional;

import javax.validation.Valid;

import com.mihaicraicun.kbn.kbn.model.Project;
import com.mihaicraicun.kbn.kbn.model.User;
import com.mihaicraicun.kbn.kbn.model.forms.ProjectForm;

public interface ProjectService {
    Optional<Project> findById(String id);
    Optional<Project> findByUserAndName(User user, String name);
    Project create(ProjectForm projectForm, User user);
    void save(Project project);
    void deleteById(String projectId);
	void update(Project project, ProjectForm projectForm);
}
