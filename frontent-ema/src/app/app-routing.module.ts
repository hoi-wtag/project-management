import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { CreateProjectComponent } from './create-project/create-project.component';
import { DetailsEmployeeComponent } from './details-employee/details-employee.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';

const routes: Routes = [
  {path: 'employees',component: EmployeeListComponent},
  {path: 'create-employee',component: CreateEmployeeComponent},
  {path: 'update-employee/:id',component: UpdateEmployeeComponent},
  {path: 'employee-details/:id',component: DetailsEmployeeComponent},
  {path: 'create-project',component: CreateProjectComponent},
  {path: '',redirectTo: 'employees',pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
