import { Component, OnInit } from '@angular/core';
import { AUTHENTICATED_USER, AuthenticationService } from '../../services/authentication/authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  checkUserLogIn: boolean = false;

  constructor( private authenticationService:AuthenticationService) { 
    
  }

  ngOnInit(): void {
    
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICATED_USER)
    return !(user === null)
  }

}
