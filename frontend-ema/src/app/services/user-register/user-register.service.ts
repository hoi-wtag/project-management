import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from 'src/app/app.constant';
import { UserAccount } from '../../models/user-account';

@Injectable({
  providedIn: 'root'
})
export class UserRegisterService {


  constructor(private httpClient: HttpClient) { }

  createUser(user: UserAccount):Observable<Object>{
    return this.httpClient.post(`${API_URL}`,user);
  }

}
