import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Employee} from '../../models/employee'
import { EmployeeService } from '../../services/employee/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees!: Employee[];
  constructor(private employeeService: EmployeeService,
    private router:Router) { }

  ngOnInit(): void {
    // this.employees=[{
    //   "id":1,
    //   "firstName":"Iqbal",
    //   "lastName":"Hossain",
    //   "emailId":"demo@aiaibt.com"
    // },
    // {
    //   "id":2,
    //   "firstName":"Jamil",
    //   "lastName":"Hasan",
    //   "emailId":"demo2@demo.com"
    // }];
    this.getEmployees();
  }
  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe(data => {
      this.employees = data;
    });
  }
  updateEmployee(employeeId : number){
    this.router.navigate(['update-employee',employeeId]);
  }
  deleteEmployee(employeeId: number){
    this.employeeService.deleteEmployee(employeeId).subscribe( data =>{
      this.getEmployees();
    }, error => console.log(error))

  }

  createEmployee(){
    this.router.navigate(['create-employee']);
  }

  employeeDetails(employeeId : number){
    this.router.navigate(['employee-details',employeeId]);
  }
}
