import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../services/employee/employee.service';
import { Project } from '../project';
import { ProjectService } from '../services/project/project.service';

@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.css']
})
export class UpdateProjectComponent implements OnInit {

  projectId!: number;
  employees!: Employee[];
  project: Project = new Project();
  constructor(private projectService: ProjectService,
    private employeeService: EmployeeService,
    private route: ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    this.projectId=this.route.snapshot.params['projectId'];
    this.projectService.getProjectById(this.projectId).subscribe(data => {
      this.project=data;
    },error => console.log(error));
    this.getEmployees();
  }

  goToProjectList(){
    this.router.navigate(['/projects']);
  }
  onSubmit() {
    this.projectService.updateProject(this.projectId,this.project).subscribe(data=>{
      this.goToProjectList();
    },error => console.log(error));
  }

  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe(data => {
      this.employees = data;
    });
  }
}
