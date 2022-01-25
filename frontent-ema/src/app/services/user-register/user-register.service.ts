import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserAccount } from '../../user-account';

@Injectable({
  providedIn: 'root'
})
export class UserRegisterService {

  private baseUrl = "http://localhost:8080/register";
  constructor(private httpClient: HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Basic ' + btoa('demo:demo123')
    })
  };

  createUser(user: UserAccount):Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}`,user,this.httpOptions);
  }

}
