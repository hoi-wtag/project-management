import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChartData } from './chart-data';
import { EmployeeProject } from './employee-project';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private baseUrl = "http://localhost:8080/home";
  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Basic ' + btoa('demo:demo123')
    })
  };

  getEmployeeProjectCountList():Observable<EmployeeProject[]>{
    return this.httpClient.get<EmployeeProject[]>(`${this.baseUrl}/employeeprojectcount`,this.httpOptions);
  }
  getProjectStatusData():Observable<ChartData[]>{
    return this.httpClient.get<ChartData[]>(`${this.baseUrl}/projectstatuscount`,this.httpOptions);
  }
}
