import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from '../../models/project';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private baseUrl = "http://localhost:8080/api/v1/projects";
  constructor(private httpClient: HttpClient) { }

  createProject(project: Project):Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`,project);
  }

  getProjectList():Observable<Project[]>{
    return this.httpClient.get<Project[]>(`${this.baseUrl}`);
  }
  getProjectWithPagination(offset: number,pageSize:number):Observable<any>{
    return this.httpClient.get<any>(`${this.baseUrl}/${offset}/${pageSize}`);
  }

  getProjectById(projectId: number):Observable<Project>{
    return this.httpClient.get<Project>(`${this.baseUrl}/${projectId}`);
  }

  updateProject(projectId: number,project: Project):Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${projectId}`,project);
  }
  
  deleteProject(id: number):Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
}
