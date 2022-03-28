import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { API_URL } from 'src/app/app.constant';
import { Jwtbalcklist } from 'src/app/models/jwtbalcklist';

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticaterUser'

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  static isUserLoggedIn: any;

  constructor(private http: HttpClient,private router: Router) { }

  executeJWTAuthenticationService(username: string, password: string) {
    
    return this.http.post<any>(
      `${API_URL}/authenticate`,{
        username,
        password
      }).pipe(
        map(
          data => {
            localStorage.setItem(AUTHENTICATED_USER, username);
            localStorage.setItem(TOKEN, `Bearer ${data.token}`);
            return data;
          }
        )
      );
  }

  getAuthenticatedUser() {
    return localStorage.getItem(AUTHENTICATED_USER)
  }

  UserBlackListed(){
     let token=this.getAuthenticatedToken()
        return this.http.post<Jwtbalcklist>(`${API_URL}/logout`,{
          token
        }
      );
  }

  getAuthenticatedToken(){
      return localStorage.getItem(TOKEN)
  }

  private tokenExpired(token: string) {
    const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
    return (Math.floor((new Date).getTime() / 1000)) >= expiry;
  }

  get isUserLoggedIn(): boolean {
    let user;
    let authenticatedToken= localStorage.getItem(TOKEN)
    if (this.tokenExpired(String(authenticatedToken))) {
      this.clearLocalStorageData();
    } else {
      user = localStorage.getItem(AUTHENTICATED_USER)
    }
    return (user !== null && authenticatedToken !== null) ? true : false;
  }

  clearLocalStorageData(){
   let authenticatedUser =localStorage.removeItem(AUTHENTICATED_USER)
   let authenticatedToek=localStorage.removeItem(TOKEN)
    if (authenticatedToek == null && authenticatedUser == null) {
      this.router.navigate(['/']);
    }
  }

  //Basic Auth configuration part
  // executeAuthenticationService(username: string, password: string) {
    
  //   let basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);

  //   let headers = new HttpHeaders({
  //       Authorization: basicAuthHeaderString
  //     })

  //   return this.http.get<AuthenticationBean>(
  //     `${API_URL}/login`,
  //     {headers}).pipe(
  //       map(
  //         data => {
  //           sessionStorage.setItem(AUTHENTICATED_USER, username);
  //           sessionStorage.setItem(TOKEN, basicAuthHeaderString);
  //           return data;
  //         }
  //       )
  //     );
  // }
  // getAuthenticatedUser() {
  //   return sessionStorage.getItem(AUTHENTICATED_USER)
  // }

  // getAuthenticatedToken(){
  //     return sessionStorage.getItem(TOKEN)
  // }

  // isUserLoggedIn() {
  //   let user = sessionStorage.getItem(AUTHENTICATED_USER)
  //   return !(user === null)
  // }

  // logout(){
  //   sessionStorage.removeItem(AUTHENTICATED_USER)
  //   sessionStorage.removeItem(TOKEN)
  // }

}

export class AuthenticationBean{
  constructor(public message:string) { }
}