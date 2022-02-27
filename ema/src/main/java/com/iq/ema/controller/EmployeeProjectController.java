package com.iq.ema.controller;

import com.iq.ema.dto.ChartData;
import com.iq.ema.dto.EmployeeProject;
import com.iq.ema.service.EmployeeService;
import com.iq.ema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeProjectController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projectsCountListPerEmployee")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EmployeeProject>> employeeProjects(){
        List<EmployeeProject> epList=employeeService.employeeProjects();
        return ResponseEntity.ok(epList);
    }

    @GetMapping("/projectStatusCount")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ChartData>> getProjectStatus(){
        List<ChartData> chartData= projectService.getProjectStatusData();
        return ResponseEntity.ok(chartData);
    }
}
