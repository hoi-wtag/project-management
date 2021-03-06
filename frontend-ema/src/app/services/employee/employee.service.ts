import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../../models/employee';
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseUrl = "http://localhost:8080/api/v1/employees";
  
  constructor(private httpClient: HttpClient ) {   }
  getEmployeesList(): Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseUrl}`);
  }

  createEmployee(employee: Employee):Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`,employee);
  }

  getEmployeeById(id: number):Observable<Employee>{
    return this.httpClient.get<Employee>(`${this.baseUrl}/${id}`);
  }

  searchEmployee(searchString: string):Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseUrl}/employeeSearchList`,
    { params: {
      searchvalue: searchString
    }  });
  }

  getEmployeeWithPagination(offset: number,pageSize:number):Observable<any>{
    return this.httpClient.get<any>(`${this.baseUrl}/${offset}/${pageSize}`);
  }

  updateEmployee(id: number,employee: Employee):Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${id}`,employee);
  }

  deleteEmployee(id: number):Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
}
