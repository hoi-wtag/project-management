import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Project } from '../project';
import { ProjectService } from '../project.service';

@Component({
  selector: 'app-details-project',
  templateUrl: './details-project.component.html',
  styleUrls: ['./details-project.component.css']
})
export class DetailsProjectComponent implements OnInit {

  projectId!: number;
  project!: Project;
  constructor(private projectService: ProjectService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.projectId=this.route.snapshot.params['projectId'];
    this.projectService.getProjectById(this.projectId).subscribe(data => {
      this.project=data;
    })

  }

}
