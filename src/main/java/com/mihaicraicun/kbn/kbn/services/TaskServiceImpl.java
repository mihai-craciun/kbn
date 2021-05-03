package com.mihaicraicun.kbn.kbn.services;

import java.util.Optional;

import com.mihaicraicun.kbn.kbn.model.Task;
import com.mihaicraicun.kbn.kbn.model.Task.State;
import com.mihaicraicun.kbn.kbn.model.requests.TaskCreationRequest;
import com.mihaicraicun.kbn.kbn.repositories.TaskRepository;

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
    public void deleteById(String id) {
        taskRepository.deleteById(id);
    }

    @Override
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
        return null;
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }
    
}
