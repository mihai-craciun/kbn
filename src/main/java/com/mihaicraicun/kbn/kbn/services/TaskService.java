package com.mihaicraicun.kbn.kbn.services;

import java.util.Optional;

import com.mihaicraicun.kbn.kbn.model.Task;
import com.mihaicraicun.kbn.kbn.model.requests.TaskCreationRequest;

public interface TaskService {
    public Optional<Task> findById(String id);
    public void deleteById(String id);
    public Task create(TaskCreationRequest taskCreationRequest);
    public void save(Task task);
}
