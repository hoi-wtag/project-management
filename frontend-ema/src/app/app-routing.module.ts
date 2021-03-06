import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateEmployeeComponent } from './components/create-employee/create-employee.component';
import { CreateProjectComponent } from './components/create-project/create-project.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DetailsEmployeeComponent } from './components/details-employee/details-employee.component';
import { DetailsProjectComponent } from './components/details-project/details-project.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { ProjectListComponent } from './components/project-list/project-list.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import { UpdateProjectComponent } from './components/update-project/update-project.component';
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { AuthGuardService } from './services/authGuard/auth-guard.service';

const routes: Routes = [
  {path: 'employees',component: EmployeeListComponent,canActivate: [AuthGuardService]},
  {path: 'create-employee',component: CreateEmployeeComponent,canActivate: [AuthGuardService]},
  {path: 'update-employee/:id',component: UpdateEmployeeComponent,canActivate: [AuthGuardService]},
  {path: 'employee-details/:id',component: DetailsEmployeeComponent,canActivate: [AuthGuardService]},
  {path: 'projects',component: ProjectListComponent,canActivate: [AuthGuardService]},
  {path: 'create-project',component: CreateProjectComponent,canActivate: [AuthGuardService]},
  {path: 'update-project/:projectId',component: UpdateProjectComponent,canActivate: [AuthGuardService]},
  {path: 'project-details/:projectId',component: DetailsProjectComponent,canActivate: [AuthGuardService]},
  {path: 'user-register',component: UserRegisterComponent},
  {path: 'dashboard',component: DashboardComponent,canActivate: [AuthGuardService]},
  {path: '',component: LoginComponent},
  {path: 'login',component: LoginComponent},
  {path: 'logout',component: LogoutComponent,canActivate: [AuthGuardService]}
  // {path: '',redirectTo: 'employees',pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
