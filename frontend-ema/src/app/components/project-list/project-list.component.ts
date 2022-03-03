import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Project } from '../../models/project';
import { ProjectService } from '../../services/project/project.service';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects!: Project[];

  config: any;

  constructor(private projectServcie: ProjectService,
    private router: Router) {
      this.config = {
        itemsPerPage: 5,
        currentPage: 1,
        totalItems: 0
      };
     }

  ngOnInit(): void {
    // this.getProjects();
    this.getProjectWithPagination();
  }
  
  private getProjects(){
    this.projectServcie.getProjectList().subscribe(data => {
      this.projects = data;
    });
  }

  getProjectWithPagination(){
    
    this.projectServcie.getProjectWithPagination(this.config.currentPage-1,this.config.itemsPerPage).subscribe(data => {
      this.config.totalItems=data.totalElements;
      this.config.itemsPerPage=data.size;
      this.projects = data.content;
    });
  }

  pageChanged(event: any){
    this.config.currentPage = event;
    console.log(this.config.currentPage,this.config.itemsPerPage);
    this.getProjectWithPagination();
  }

  createProject(){
    this.router.navigate(['create-project']);
  }
  updateProject(projectId: number){
    this.router.navigate(['update-project',projectId]);
  }

  deleteProject(projectId: number){
    this.projectServcie.deleteProject(projectId).subscribe( data =>{
      // this.getProjects();
      this.getProjectWithPagination();
    }, error => console.log(error))
  }

  projectDetails(projectId: number){
    this.router.navigate(['project-details',projectId]);
  }
}
