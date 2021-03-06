package com.mihaicraciun.kbn.kbn.services;

import java.util.Optional;

import com.mihaicraciun.kbn.kbn.model.Task;
import com.mihaicraciun.kbn.kbn.model.requests.TaskCreationRequest;
import com.mihaicraciun.kbn.kbn.model.requests.TaskMoveRequest;
import com.mihaicraciun.kbn.kbn.model.requests.TaskUpdateRequest;

public interface TaskService {
    public Optional<Task> findById(String id);
    public void deleteById(String id);
    public Task create(TaskCreationRequest taskCreationRequest);
    public void save(Task task);
    public void update(TaskUpdateRequest taskUpdateRequest);
    public void moveTask(TaskMoveRequest taskMoveRequest);
}
