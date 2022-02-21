package com.iq.ema.controller;

import com.iq.ema.exceptions.ResourceNotFoundException;
import com.iq.ema.model.Project;
import com.iq.ema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping(path="/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody Project project){
        return projectService.save(project);
    }

    // Get Project by ID restapi

    @GetMapping("/projects/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project getProjectById(@PathVariable long id){
        Project project= projectService.findByProjectId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id:"+id));
        return project;
    }

    // update project by id rest api

    @PutMapping("/projects/{id}")
    public Project updateProject(@PathVariable long id,@RequestBody  Project projectDetails){
        Project project= projectService.findByProjectId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id:"+id));
        Project updateProject= projectService.save(projectDetails);
        return updateProject;
    }

    // delete project rest api
    @DeleteMapping("/projects/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Long id){
        Project project= projectService.findByProjectId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id:"+id));
        projectService.delete(project);
    }

}
