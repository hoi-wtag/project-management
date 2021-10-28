import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from './project';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private baseUrl = "http://localhost:8080/api/v1/projects";
  constructor(private httpClient: HttpClient) { }

  createProject(project: Project):Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`,project);
  }
}
