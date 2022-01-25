import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAccount } from '../user-account';
import { UserRegisterService } from '../services/user-register/user-register.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  user: UserAccount = new UserAccount();
  constructor(private userRegisterService: UserRegisterService,
    private router:Router) { }

  ngOnInit(): void {
  }

  saveUser(){
    this.userRegisterService.createUser(this.user).subscribe(data=>{
      console.log(data);
      this.router.navigate(['login']);
    },
    error => console.log(error));
  }
  onSubmit() {
    this.saveUser();
  }

}
