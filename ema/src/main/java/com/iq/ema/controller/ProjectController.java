package com.iq.ema.controller;

import com.iq.ema.dto.ApiResponse;
import com.iq.ema.dto.ProjectDTO;
import com.iq.ema.dto.ProjectListDTO;
import com.iq.ema.exceptions.ResourceNotFoundException;
import com.iq.ema.model.Project;
import com.iq.ema.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ModelMapper modelMapper;

    // Get all projects

    @GetMapping("/projects")
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectListDTO> getAllProjects(){
        return  projectService.getAll().stream().map(projects -> modelMapper.map(projects, ProjectListDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/projects/{pageNumber}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<ProjectListDTO>> getEmployeesWithPagination(@PathVariable int pageNumber, @PathVariable int pageSize){
        Page<ProjectListDTO> projectWithPagination = projectService.findProjectWithPagination(pageNumber, pageSize).
                map(projects -> modelMapper.map(projects, ProjectListDTO.class));
        return  ResponseEntity.ok(projectWithPagination);
    }
    // create Project restapi

    @PostMapping(path="/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjectDTO> createProject(@RequestBody @Valid Project project){
        Project prObj= projectService.save(project);
        // convert entity to DTO
        ProjectDTO projectResponse=modelMapper.map(prObj,ProjectDTO.class);
        return ResponseEntity.ok(projectResponse);
    }

    // Get Project by ID restapi

    @GetMapping("/projects/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable long id){
        Project project= projectService.findByProjectId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id:"+id));
        ProjectDTO projectResponse=modelMapper.map(project,ProjectDTO.class);
        return ResponseEntity.ok(projectResponse);
    }

    // update project by id rest api

    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable long id,@RequestBody  ProjectDTO projectDTO){
        // convert DTO to entity
        Project projectRequest=modelMapper.map(projectDTO,Project.class);

        Project updateProject= projectService.update(id,projectRequest);
        ProjectDTO projectResponse=modelMapper.map(updateProject,ProjectDTO.class);
        return ResponseEntity.ok(projectResponse);
    }

    // delete project rest api
    @DeleteMapping("/projects/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable Long id){
        Project project= projectService.findByProjectId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not exist with id:"+id));
        projectService.delete(project);
        ApiResponse apiResponse = new ApiResponse("Project deleted successfully with id:"+id);
        return ResponseEntity.ok(apiResponse);
    }

}
