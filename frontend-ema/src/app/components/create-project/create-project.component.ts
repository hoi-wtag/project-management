import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../../models/employee';
import { FormBuilder, Validators } from "@angular/forms";
import { EmployeeService } from '../../services/employee/employee.service';
import { Project } from '../../models/project';
import { ProjectService } from '../../services/project/project.service';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {


  project: Project = new Project();
  employees!: Employee[];
  selectedEmployees!: number[];
  constructor(private projectService: ProjectService,
    private employeeService: EmployeeService,
    private router:Router) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  saveProject(){
    this.projectService.createProject(this.project).subscribe(data=>{
      console.log(data);
      this.goToProjectList();
    },
    error => console.log(error));
  }

  goToProjectList(){
    this.router.navigate(['projects']);
  }

  onSubmit(){
    this.saveProject();
    console.log(this.project);
  }

  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe(data => {
      this.employees = data;
    });
  }
}
