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
  config: any;
  searchString!: string;

  constructor(private employeeService: EmployeeService,
    private router:Router) {
      this.config = {
        itemsPerPage: 5,
        currentPage: 1,
        totalItems: 0
      };
     }

  ngOnInit(): void {
    
    // this.getEmployees();
    //this.getEmployeeWithPagination();
    if(this.searchString==undefined){
      this.getEmployeeWithPagination();
    }else{
      this.searchEmployees()
    }
  }


   searchEmployees(){
    this.employees=[];
    this.config.totalItems=0;
    this.config.itemsPerPage=5;
    this.config.currentPage=1;
    this.employeeService.searchEmployee(this.searchString).subscribe(data => {
      this.config.totalItems=data.length;
      this.config.itemsPerPage=5;
      this.config.currentPage=this.config.currentPage-1;
      this.employees = data;
    });
  }


  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe(data => {
      this.employees = data;
    });
  }
  updateEmployee(employeeId : number){
    this.router.navigate(['update-employee',employeeId]);
  }

  getEmployeeWithPagination(){
    
    this.employeeService.getEmployeeWithPagination(this.config.currentPage-1,this.config.itemsPerPage).subscribe(data => {
      this.config.totalItems=data.totalElements;
      this.config.itemsPerPage=data.size;
      this.employees = data.content;
    });
  }

  pageChanged(event: any){
    this.config.currentPage = event;
    console.log(this.config.currentPage,this.config.itemsPerPage);
    if(this.searchString==undefined){
      this.getEmployeeWithPagination();
    }
  }

  deleteEmployee(employeeId: number){
    this.employeeService.deleteEmployee(employeeId).subscribe( data =>{
      this.getEmployeeWithPagination();
    }, error => console.log(error))

  }

  createEmployee(){
    this.router.navigate(['create-employee']);
  }

  employeeDetails(employeeId : number){
    this.router.navigate(['employee-details',employeeId]);
  }
}
