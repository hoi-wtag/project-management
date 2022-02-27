import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/app/app.constant';
import { ChartData } from '../../models/chart-data';
import { EmployeeProject } from '../../models/employee-project';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private httpClient: HttpClient) { }


  getEmployeeProjectCountList():Observable<EmployeeProject[]>{
    return this.httpClient.get<EmployeeProject[]>(`${API_URL}/projectCountListPerEmployee`);
  }
  getProjectStatusData():Observable<ChartData[]>{
    return this.httpClient.get<ChartData[]>(`${API_URL}/projectStatusCount`);
  }
}
