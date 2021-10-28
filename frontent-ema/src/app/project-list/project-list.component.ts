import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../project';
import { ProjectService } from '../project.service';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects!: Project[];

  constructor(private projectServcie: ProjectService,
    private router: Router) { }

  ngOnInit(): void {
    this.getProjects();
  }
  
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
