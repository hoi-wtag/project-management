import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/authentication/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = ''
  password = ''
  errorMessage = 'Invalid Credentials'
  invalidLogin = false

  constructor(private router: Router,private authenticationService:AuthenticationService) { }

  ngOnInit(): void {
  }

  handleBasicAuthLogin() {
    this.authenticationService.executeJWTAuthenticationService(this.username, this.password)
        .subscribe(
          data => {
            this.router.navigate(['dashboard'])
            this.invalidLogin = false      
          },
          error => {
            console.log(error)
            this.invalidLogin = true
          }
        )
  }

}
