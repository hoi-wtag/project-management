import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Project } from '../project';
import { ProjectService } from '../project.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  employees!: Employee[];
  projects!: Project[];
  constructor(private employeeService: EmployeeService,
    private router:Router,
    private projectServcie: ProjectService) { }

  ngOnInit(): void {
    this.getEmployees();
    this.getProjects();
  }
// employee////
  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe(data => {
      this.employees = data;
      this.getProjects();
    });
  }
  //// Projects ////
  private getProjects(){
    this.projectServcie.getProjectList().subscribe(data => {
      this.projects = data;
    });
  }
}
