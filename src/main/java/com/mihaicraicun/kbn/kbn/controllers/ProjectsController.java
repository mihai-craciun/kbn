package com.mihaicraciun.kbn.kbn.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.mihaicraicun.kbn.kbn.model.forms.ProjectForm;
import com.mihaicraicun.kbn.kbn.services.ProjectService;
import com.mihaicraicun.kbn.kbn.services.WorksOnService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/projects")
public class ProjectsController extends BaseController {

    @Autowired
    ProjectService projectService;

    @Autowired
    WorksOnService worksOnService;
    
    @GetMapping("")
    public String getProjects(Model model) {
        model.addAttribute("ownProjects", userService.currentUser().getProjects());
        model.addAttribute("teamProjects", worksOnService.getProjectsByUser(userService.currentUser()));
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

        if (projectService.findByUserAndName(userService.currentUser(), projectFormObject.getName()).isPresent()) {
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
            Project project = projectService.create(projectFormObject, userService.currentUser());
            return "redirect:/projects/" + project.getId();
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/{id}")
    public String getProject(@PathVariable String id, Model model) {
        Project project = projectService.getProjectByIdVisibleByCurrentUser(id);
        model.addAttribute("project", project);
        return "projects/project";
    }

    @GetMapping("/{id}/edit")
    public String editProject(@PathVariable String id, Model model) {
        Project project = projectService.getProjectByIdEditableByCurrentUser(id);
        model.addAttribute("project", project);

        // fill form
        ProjectForm projectForm = new ProjectForm();
        projectForm.setName(project.getName());
        projectForm.setDescription(project.getDescription());
        projectForm.setIsPrivate(project.getIsPrivate());
        model.addAttribute("projectFormObject", projectForm);
        
        return "projects/update";
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable String id) {
        Project project = projectService.getProjectByIdEditableByCurrentUser(id);
        projectService.deleteById(project.getId());
        return "redirect:/projects";
    }

    @PutMapping("/{id}")
    public String updateProject(@PathVariable String id,
                           @Valid @ModelAttribute("projectFormObject") ProjectForm projectFormObject,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        Project project = projectService.getProjectByIdEditableByCurrentUser(id);

        Map<String, List<String>> errors;

        if (bindingResult.hasErrors()) {
            errors = ErrorHandler.convertBindingResultErrorsToMap(bindingResult);
        } else {
            errors = new HashMap<>();
        }

        Optional<Project> projectWithSameName = projectService.findByUserAndName(userService.currentUser(), projectFormObject.getName());
        if (projectWithSameName.isPresent() && !projectWithSameName.get().getId().equals(id)) {
            if (!errors.containsKey("name"))
                errors.put("name", new ArrayList<String>());
            errors.get("name").add("You already have a project created with the same name");
        }

        if (errors.size() > 0) {
            for (String key : errors.keySet()) {
                System.out.println(errors.get(key));
                redirectAttributes.addFlashAttribute(key + "Error", errors.get(key));
            }
            return "redirect:/projects/" + id + "/edit";
        }

        try {
            projectService.update(project, projectFormObject);
            redirectAttributes.addFlashAttribute("updatedSuccesfully", true);
            return "redirect:/projects/" + id + "/edit";
        } catch (Exception e) {
            return "error";
        }
    }
}
