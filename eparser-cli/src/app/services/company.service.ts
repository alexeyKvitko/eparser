import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {GlobalService} from "./global.service";
import {Observable} from "rxjs";
import {ApiResponse} from "../model/api.response";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private companyUrl = '/api/companies';
  private apiUrl = '/api';

  constructor(private http: HttpClient,private _globalService: GlobalService) {
  }

  public getCompanies() : Observable<ApiResponse> {
    return this.http.get<ApiResponse>( this.companyUrl );
  }

}
