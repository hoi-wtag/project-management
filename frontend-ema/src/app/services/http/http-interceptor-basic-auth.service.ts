import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorBasicAuthService implements HttpInterceptor{

  constructor(private authenticationService:AuthenticationService ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler){
    
    let basicAuthHeaderString = this.authenticationService.getAuthenticatedToken();
    let username = this.authenticationService.getAuthenticatedUser()

    if(basicAuthHeaderString && username) { 
      request = request.clone({
        setHeaders : {
            Authorization : basicAuthHeaderString
          }
        }) 
    }
    return next.handle(request);
  }
}
