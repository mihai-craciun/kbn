package com.mihaicraicun.kbn.kbn.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.mihaicraicun.kbn.kbn.misc.ErrorHandler;
import com.mihaicraicun.kbn.kbn.model.Project;
import com.mihaicraicun.kbn.kbn.model.User;
import com.mihaicraicun.kbn.kbn.model.forms.ProjectForm;
import com.mihaicraicun.kbn.kbn.services.ProjectService;
import com.mihaicraicun.kbn.kbn.services.WorksOnService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/projects")
public class ProjectsController extends BaseController {

    @Autowired
    ProjectService projectService;

    @Autowired
    WorksOnService worksOnService;
    
    @GetMapping("")
    public String getProjects(Model model) {
        model.addAttribute("ownProjects", getCurrentUser().getProjects());
        model.addAttribute("teamProjects", worksOnService.getProjectsByUser(getCurrentUser()));
        return "projects/all";
    }

    @GetMapping("/create")
    public String getProjectCreationPage(Model model) {
        model.addAttribute("projectFormObject", new ProjectForm());
        return "projects/create";
    }

    @PostMapping("/create")
    public String createProject(@Valid @ModelAttribute("projectFormObject") ProjectForm projectFormObject,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        Map<String, List<String>> errors;

        if (bindingResult.hasErrors()) {
            errors = ErrorHandler.convertBindingResultErrorsToMap(bindingResult);
        } else {
            errors = new HashMap<>();
        }

        if (projectService.findByUserAndName(getCurrentUser(), projectFormObject.getName()).isPresent()) {
            if (!errors.containsKey("name"))
                errors.put("name", new ArrayList<String>());
            errors.get("name").add("You already have a project created with the same name");
        }

        if (errors.size() > 0) {
            for (String key : errors.keySet()) {
                System.out.println(errors.get(key));
                redirectAttributes.addFlashAttribute(key + "Error", errors.get(key));
            }
            return "redirect:/projects/create";
        }

        try {
            Project project = projectService.create(projectFormObject, getCurrentUser());
            return "redirect:/projects/" + project.getId();
        } catch (Exception e) {
            return "error";
        }
    }
    
}
