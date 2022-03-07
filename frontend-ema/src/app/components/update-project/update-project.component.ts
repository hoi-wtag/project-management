import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../../models/employee';
import { EmployeeService } from '../../services/employee/employee.service';
import { Project } from '../../models/project';
import { ProjectService } from '../../services/project/project.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.css']
})
export class UpdateProjectComponent implements OnInit {

  projectId!: number;
  employees!: Employee[];
  project: Project = new Project();
  filteredEmployees!: Employee[];
  selectedEmployees!: Employee[];

  dropdownList!:any[];

  dropdownListwithData!:any[];
  selectedItems!:any[];
  selected!:any[];
  dropdownSettings:IDropdownSettings={};
  dropDownForm!: FormGroup;
  constructor(private projectService: ProjectService,
    private employeeService: EmployeeService,
    private route: ActivatedRoute,private router:Router,private fb: FormBuilder) { }

  ngOnInit(): void {
    this.projectId=this.route.snapshot.params['projectId'];
    this.projectService.getProjectById(this.projectId).subscribe(data => {
      this.project=data;
      this.project.employees=data.employees;
      this.getEmployees();
    },error => console.log(error));
    

    this.dropdownSettings = {
      idField: 'employeeId',
      textField: 'firstName',
      allowSearchFilter: true
    };

    this.dropDownForm = this.fb.group({
      myItems: [this.selectedItems]
  });
  }

  goToProjectList(){
    this.router.navigate(['/projects']);
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

  onSubmit() {
    this.projectService.updateProject(this.projectId,this.project).subscribe(data=>{
      this.goToProjectList();
    },error => console.log(error));
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
        firstName: item.firstName
      }))
      this.selected =  this.dropdownListwithData.filter(a => this.project.employees.some((b => b.employeeId === a.employeeId && b.firstName === a.firstName)));
    });
  }
}
