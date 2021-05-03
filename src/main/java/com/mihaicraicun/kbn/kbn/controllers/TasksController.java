package com.mihaicraicun.kbn.kbn.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.mihaicraicun.kbn.kbn.misc.ErrorHandler;
import com.mihaicraicun.kbn.kbn.model.Project;
import com.mihaicraicun.kbn.kbn.model.Task;
import com.mihaicraicun.kbn.kbn.model.requests.TaskCreationRequest;
import com.mihaicraicun.kbn.kbn.model.responses.TaskCreationResponse;
import com.mihaicraicun.kbn.kbn.services.ProjectService;
import com.mihaicraicun.kbn.kbn.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
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
    public TaskCreationResponse createTask(@Valid TaskCreationRequest request, BindingResult bindingResult) {
        
        TaskCreationResponse response = new TaskCreationResponse();
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
}
