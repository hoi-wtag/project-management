import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../../models/employee';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { EmployeeService } from '../../services/employee/employee.service';
import { Project } from '../../models/project';
import { ProjectService } from '../../services/project/project.service';
import { IDropdownSettings, ListItem } from 'ng-multiselect-dropdown/multiselect.model';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {
  
  totalEmployee!:number;
  project: Project = new Project();
  employees!: Employee[];
  filteredEmployees!: Employee[];
  selectedEmployees!: number[];

  dropdownList!:any[];

  dropdownListwithData:any = [];
  selectedItems!:any[];
  selected!:any[];
  dropdownSettings:IDropdownSettings={};
  dropDownForm!: FormGroup;
  
  constructor(private projectService: ProjectService,
    private employeeService: EmployeeService,
    private router:Router,private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getEmployees();

    this.dropdownSettings = {
      idField: 'employeeId',
      textField: 'firstName',
      allowSearchFilter: true
    };

    this.dropDownForm = this.fb.group({
      myItems: [this.selectedItems]
  });
  }

  saveProject(){
    this.projectService.createProject(this.project).subscribe(data=>{
      console.log(data);
      this.goToProjectList();
    },
    error => console.log(error));
  }

  goToProjectList(){
    this.router.navigate(['projects']);
  }

  onItemSelect(event:any){
    if(this.selected.length>0){
      this.getFilteredEmployees();
    }
  }
  onSelectAll(items: any) {
    this.selected=items;
    if(this.selected.length>0){
      this.getFilteredEmployees();
    }
  }
  onDeSelectAll(items: any) {
    this.selected=items;
    if(this.selected.length==0){
      this.getFilteredEmployees();
    }
  }

  onSubmit(){
    this.saveProject();
  }

  private getFilteredEmployees(){
    this.filteredEmployees = this.employees.filter(a => this.selected.some((b => b.employeeId === a.employeeId && b.firstName === a.firstName)));
    this.project.employees=this.filteredEmployees;
  }

  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe(data => {
      this.employees = data;
      this.dropdownListwithData=data.map(item=>({
        employeeId: item.employeeId,
        firstName: item.firstName,
        lastName: item.lastName
      }))
    });
  }
}
