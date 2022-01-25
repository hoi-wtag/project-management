import { Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import {SingleDataSet, Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip } from 'ng2-charts';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../services/employee/employee.service';
import { Project } from '../project';
import { ProjectService } from '../services/project/project.service';
import { DashboardService } from '../services/dashboard/dashboard.service';
import { EmployeeProject } from '../employee-project';
import { ChartData } from '../chart-data';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  employees!: Employee[];
  projects!: Project[];
  employeeProjects!: EmployeeProject[];
  chartDatas!: ChartData[];
  constructor(private employeeService: EmployeeService,
    private dashboardService: DashboardService,
    private router:Router,
    
    private projectServcie: ProjectService) {
      monkeyPatchChartJsTooltip();
      monkeyPatchChartJsLegend();
     }

    public pieChartOptions: ChartOptions = {
      responsive: true,
    };
    public pieChartLabels: Label[] = [];
    public pieChartData: SingleDataSet = [];
    public pieChartType: ChartType = 'pie';
    public pieChartLegend = true;
    public pieChartPlugins = [];
    public pieChartColors: Array < any > = [{
      backgroundColor: ['#dcf858', '#19d863', '#fdf57d']
  }];
  ngOnInit(): void {
    this.getEmployees();
    this.getProjects();
    this.getEmplyeeProjectCountList();
    this.getProjectStatus();
  }
// employee////
  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe(data => {
      this.employees = data;
      this.getProjects();
    });
  }
  //// Projects ////
  private getProjects(){
    this.projectServcie.getProjectList().subscribe(data => {
      this.projects = data;
    });
  }
  //// Employee Projects count////
  private getEmplyeeProjectCountList(){
    this.dashboardService.getEmployeeProjectCountList().subscribe(data => {
      this.employeeProjects = data;
    });
  }

  //// Projects status count////
  private getProjectStatus(){
    this.dashboardService.getProjectStatusData().subscribe(data => {
      this.chartDatas = data;
      this.pieChartLabels=this.chartDatas.map(({label}) => label);
      this.pieChartData=this.chartDatas.map(({value}) => value);
    });
  }
}
