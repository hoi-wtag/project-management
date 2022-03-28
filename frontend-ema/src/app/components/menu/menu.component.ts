import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/app/app.constant';
import { Jwtbalcklist } from 'src/app/models/jwtbalcklist';
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
    let user = localStorage.getItem(AUTHENTICATED_USER)
    return !(user === null)
  }

  logout(){
    this.authenticationService.UserBlackListed().subscribe(data=>{
      // this.authenticationService.clearLocalStorageData();
    },
    error => console.log(error));
  }

}
