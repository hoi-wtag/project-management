import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { ChartsModule } from 'ng2-charts';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateEmployeeComponent } from './components/create-employee/create-employee.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import { DetailsEmployeeComponent } from './components/details-employee/details-employee.component';
import { CreateProjectComponent } from './components/create-project/create-project.component';
import { UpdateProjectComponent } from './components/update-project/update-project.component';
import { DetailsProjectComponent } from './components/details-project/details-project.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { ProjectListComponent } from './components/project-list/project-list.component';
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { LoginComponent } from './components/login/login.component';
import { HttpInterceptorBasicAuthService } from './services/http/http-interceptor-basic-auth.service';
import { MenuComponent } from './components/menu/menu.component';
import { LogoutComponent } from './components/logout/logout.component';


@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
    CreateEmployeeComponent,
    UpdateEmployeeComponent,
    DetailsEmployeeComponent,
    ProjectListComponent,
    CreateProjectComponent,
    UpdateProjectComponent,
    DetailsProjectComponent,
    DashboardComponent,
    UserRegisterComponent,
    LoginComponent,
    MenuComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ChartsModule,
    ReactiveFormsModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass:HttpInterceptorBasicAuthService , multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
