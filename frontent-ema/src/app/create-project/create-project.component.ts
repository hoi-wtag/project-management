import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Project } from '../project';
import { ProjectService } from '../project.service';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {

  project: Project = new Project();
  constructor(private projectService: ProjectService,
    private router:Router) { }

  ngOnInit(): void {
  }

  saveProject(){
    this.projectService.createProject(this.project).subscribe(data=>{
      console.log(data);
      this.goToProjectList();
    },
    error => console.log(error));
  }

  goToProjectList(){
    this.router.navigate(['/']);
  }

  onSubmit(){
    this.saveProject();
    console.log(this.project);
  }
}
