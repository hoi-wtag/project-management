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

  updateEmployee(id : number){
    this.router.navigate(['update-employee',id]);
  }
  deleteEmployee(id: number){
    this.employeeService.deleteEmployee(id).subscribe( data =>{
      this.getEmployees();
    }, error => console.log(error))

  }

  employeeDetails(id : number){
    this.router.navigate(['employee-details',id]);
  }
  //// Projects ////
  private getProjects(){
    this.projectServcie.getProjectList().subscribe(data => {
      this.projects = data;
    });
  }

  updateProject(projectId: number){
    this.router.navigate(['update-project',projectId]);
  }

  deleteProject(projectId: number){
    this.projectServcie.deleteProject(projectId).subscribe( data =>{
      this.getProjects();
    }, error => console.log(error))
  }

  projectDetails(projectId: number){
    this.router.navigate(['project-details',projectId]);
  }
}
