package com.mihaicraicun.kbn.kbn.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.mihaicraicun.kbn.kbn.controllers.exceptions.ProjectNotFoundException;
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

    @Autowired
    UserService userService;

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
    @Transactional
    public void deleteById(String projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public Optional<Project> findByUserAndName(User user, String name) {
        return projectRepository.findByOwnerAndName(user, name);
    }

    @Override
    @Transactional
    public void update(Project project, ProjectForm projectForm) {
        project.setName(projectForm.getName());
        project.setDescription(projectForm.getDescription());
        project.setIsPrivate(projectForm.getIsPrivate());
        save(project);
    }

    @Override
    public Project getProjectByIdVisibleByCurrentUser(String id) throws ProjectNotFoundException{
        Optional<Project> projectContainer = findById(id);
        if (!projectContainer.isPresent()) {
            throw new ProjectNotFoundException();
        }
        Project project = projectContainer.get();
        User user = userService.currentUser();
        if (project.getIsPrivate()) {
            boolean isOwner = project.getOwner().equals(user);
            // TODO user should be able to view project if the 
            boolean thisUserWorksOnProject = true;
            if (!isOwner && !thisUserWorksOnProject) {
                throw new ProjectNotFoundException();
            }
        }
        return projectContainer.get();
    }

    @Override
    public Project getProjectByIdEditableByCurrentUser(String id) throws ProjectNotFoundException {
        Project project = getProjectByIdVisibleByCurrentUser(id);
        if (!project.getOwner().equals(userService.currentUser())) {
            throw new ProjectNotFoundException();
        }
        return project;
    }
    
}
