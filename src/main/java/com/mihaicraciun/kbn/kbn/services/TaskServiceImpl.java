package com.mihaicraciun.kbn.kbn.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.mihaicraciun.kbn.kbn.controllers.exceptions.TaskNotFoundException;
import com.mihaicraciun.kbn.kbn.model.Task;
import com.mihaicraciun.kbn.kbn.model.Task.State;
import com.mihaicraciun.kbn.kbn.model.requests.TaskCreationRequest;
import com.mihaicraciun.kbn.kbn.model.requests.TaskMoveRequest;
import com.mihaicraciun.kbn.kbn.model.requests.TaskUpdateRequest;
import com.mihaicraciun.kbn.kbn.repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectService projectService;

    @Autowired
    UserService userService;

    @Override
    public Optional<Task> findById(String id) {
        return taskRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Optional<Task> taskContainer = findById(id);
        if (!taskContainer.isPresent()) {
            throw new TaskNotFoundException();
        }
        Task task = taskContainer.get();
        // Test for project accesibility
        projectService.getProjectByIdVisibleByCurrentUser(task.getProject().getId());
        taskRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Task create(TaskCreationRequest taskCreationRequest) {
        Task task = new Task();
        task.setAsignee(userService.findByEmail(taskCreationRequest.getAsigneeEmail()));
        task.setName(taskCreationRequest.getName());
        task.setDescription(taskCreationRequest.getDescription());
        task.setOwner(userService.currentUser());
        task.setStoryPoints(taskCreationRequest.getStoryPoints());
        task.setPriorityType(taskCreationRequest.getPriorityType());
        task.setTaskState(State.TO_DO);
        task.setTaskType(taskCreationRequest.getTaskType());
        task.setProject(projectService.getProjectByIdVisibleByCurrentUser(taskCreationRequest.getProjectId()));
        save(task);
        return task;
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void update(TaskUpdateRequest taskUpdateRequest) {
        Optional<Task> taskContainer = findById(taskUpdateRequest.getId());
        if (!taskContainer.isPresent()) {
            throw new TaskNotFoundException();
        }
        Task task = taskContainer.get();
        // Test for project accesibility
        projectService.getProjectByIdVisibleByCurrentUser(task.getProject().getId());
        task.setName(taskUpdateRequest.getName());
        task.setDescription(taskUpdateRequest.getDescription());
        task.setPriorityType(taskUpdateRequest.getPriorityType());
        task.setTaskType(taskUpdateRequest.getTaskType());
        task.setStoryPoints(taskUpdateRequest.getStoryPoints());
        save(task);
    }

    @Override
    @Transactional
    public void moveTask(TaskMoveRequest taskMoveRequest) {
        Optional<Task> taskContainer = findById(taskMoveRequest.getId());
        if (!taskContainer.isPresent()) {
            throw new TaskNotFoundException();
        }
        Task task = taskContainer.get();
        // Test for project accesibility
        projectService.getProjectByIdVisibleByCurrentUser(task.getProject().getId());
        task.setTaskState(taskMoveRequest.getTaskState());
        save(task);
        
    }
    
}
