package com.mihaicraciun.kbn.kbn.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.mihaicraciun.kbn.kbn.misc.ErrorHandler;
import com.mihaicraciun.kbn.kbn.model.requests.TaskCreationRequest;
import com.mihaicraciun.kbn.kbn.model.requests.TaskDeleteRequest;
import com.mihaicraciun.kbn.kbn.model.requests.TaskMoveRequest;
import com.mihaicraciun.kbn.kbn.model.requests.TaskUpdateRequest;
import com.mihaicraciun.kbn.kbn.model.responses.TaskResponse;
import com.mihaicraciun.kbn.kbn.services.ProjectService;
import com.mihaicraciun.kbn.kbn.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TasksController extends BaseController {
    
    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @PostMapping("")
    public TaskResponse createTask(@Valid @RequestBody TaskCreationRequest request, BindingResult bindingResult) {
        
        TaskResponse response = new TaskResponse();
        response.setHasErrors(false);

        Map<String, List<String>> errors;
        
        if (bindingResult.hasErrors()) {
            errors = ErrorHandler.convertBindingResultErrorsToMap(bindingResult);
            response.setHasErrors(true);
            response.setErrors(errors);
            return response;
        }

        taskService.create(request);
        return response;
    }

    @PutMapping("")
    public TaskResponse updateTask(@Valid @RequestBody TaskUpdateRequest request, BindingResult bindingResult) {
        
        TaskResponse response = new TaskResponse();
        response.setHasErrors(false);

        Map<String, List<String>> errors;

        if (bindingResult.hasErrors()) {
            errors = ErrorHandler.convertBindingResultErrorsToMap(bindingResult);
            response.setHasErrors(true);
            response.setErrors(errors);
            return response;
        }

        taskService.update(request);
        return response;
    }

    @PatchMapping("")
    public TaskResponse moveTask(@Valid @RequestBody TaskMoveRequest request, BindingResult bindingResult) {
        TaskResponse response = new TaskResponse();
        response.setHasErrors(false);

        Map<String, List<String>> errors;

        if (bindingResult.hasErrors()) {
            errors = ErrorHandler.convertBindingResultErrorsToMap(bindingResult);
            response.setHasErrors(true);
            response.setErrors(errors);
            return response;
        }

        taskService.moveTask(request);
        return response;
    }

    @DeleteMapping("")
    public TaskResponse deleteTask(@Valid @RequestBody TaskDeleteRequest request, BindingResult bindingResult) {
        TaskResponse response = new TaskResponse();
        response.setHasErrors(false);

        Map<String, List<String>> errors;

        if (bindingResult.hasErrors()) {
            errors = ErrorHandler.convertBindingResultErrorsToMap(bindingResult);
            response.setHasErrors(true);
            response.setErrors(errors);
            return response;
        }

        taskService.deleteById(request.getId());
        return response;
    }
}
