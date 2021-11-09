package com.iq.ema.controller;

import com.iq.ema.exceptions.ResourceNotFoundException;
import com.iq.ema.model.Employee;
import com.iq.ema.model.Project;
import com.iq.ema.service.EmployeeService;
import com.iq.ema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;
    // Get all projects

    @GetMapping("/projects")
    public List<Project> getAllProjects(){

        return (List<Project>) projectService.getAll();
    }

    // create Project restapi

    @PostMapping(path="/projects")
    public Project createProject(@RequestBody Project project){
//        project.getEmployees().forEach(emp->emp.setProjects(List.of(project)));
        return projectService.save(project);
    }

    // Get Project by ID restapi

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable long id){
        Project project= projectService.findByProjectId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id:"+id));
        return ResponseEntity.ok(project);
    }

    // update project by id rest api

    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateEmployee(@PathVariable long id,@RequestBody  Project projectDetails){
        Project project= projectService.findByProjectId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id:"+id));
        project.setName(projectDetails.getName());
        project.setStage(projectDetails.getStage());
        project.setDescription(projectDetails.getDescription());

        Project updateProject= projectService.save(project);
        return ResponseEntity.ok(updateProject);
    }

    // delete project rest api
    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProject(@PathVariable Long id){
        Project project= projectService.findByProjectId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id:"+id));

        projectService.delete(project);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
