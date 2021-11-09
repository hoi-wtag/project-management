package com.iq.ema.controller;

import com.iq.ema.dto.ChartData;
import com.iq.ema.dto.EmployeeProject;
import com.iq.ema.model.Employee;
import com.iq.ema.service.EmployeeService;
import com.iq.ema.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/employeeprojectcount")
    public List<EmployeeProject> employeeProjects(){

        return (List<EmployeeProject>) employeeService.employeeProjects();
    }

    @GetMapping("/projectstatuscount")
    public List<ChartData> getProjectStatus(){

        return (List<ChartData>) projectService.getProjectStatusData();
    }
}
