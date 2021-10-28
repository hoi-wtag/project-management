package com.iq.ema.controller;

import com.iq.ema.model.Employee;
import com.iq.ema.model.Project;
import com.iq.ema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Get all projects

    @GetMapping("/projects")
    public List<Project> getAllProjects(){

        return (List<Project>) projectService.getAll();
    }

    // create Project restapi

    @PostMapping("/projects")
    public Project createProject(@RequestBody Project project){

        return projectService.save(project);
    }
}
