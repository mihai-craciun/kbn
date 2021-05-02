package com.mihaicraicun.kbn.kbn.services;

import java.util.Optional;

import com.mihaicraicun.kbn.kbn.model.Project;
import com.mihaicraicun.kbn.kbn.model.User;
import com.mihaicraicun.kbn.kbn.model.forms.ProjectForm;
import com.mihaicraicun.kbn.kbn.repositories.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Optional<Project> findById(String id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project create(ProjectForm projectForm, User user) {
        Project project = new Project();
        project.setName(projectForm.getName());
        project.setDescription(projectForm.getDescription());
        project.setIsPrivate(projectForm.getIsPrivate());
        project.setOwner(user);
        save(project);
        return project;
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
        
    }

    @Override
    public void deleteById(String projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public Optional<Project> findByUserAndName(User user, String name) {
        return projectRepository.findByOwnerAndName(user, name);
    }
    
}
