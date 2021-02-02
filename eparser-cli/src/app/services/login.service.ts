import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from "../model/user.model";
import {Observable} from "rxjs/index";
import {ApiResponse} from "../model/api.response";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) {}
  tokenUrl = '/token/generate-token';

  login(loginPayload) : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.tokenUrl, loginPayload);
  }



  getIP(): Observable<any> {
    // jQuery.getJSON("https://jsonip.com?callback=?", function(data) {
    //   alert("Your IP address is :- " + data.ip);
    // });
    return this.http.get('https://ipinfo.io/json',{ responseType: 'json' });
    // .subscribe(data => {
    //   console.log("DATA",data);
    // }, error => {
    //   console.log('Error: ',  error);
    // });
    // .catch((error:any) => Observable.throw(error.json().error || 'Server error')); //...errors if any
  }


}
