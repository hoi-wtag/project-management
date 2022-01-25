import { Component, OnInit } from '@angular/core';
import { AUTHENTICATED_USER, BasicAuthenticationService } from '../services/basicAuthentication/basic-authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  checkUserLogIn: boolean = false;

  constructor( private basicAuthenticationService:BasicAuthenticationService) { 
    
  }

  ngOnInit(): void {
    
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICATED_USER)
    return !(user === null)
  }

}
