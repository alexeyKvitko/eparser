import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {GlobalService} from "./global.service";
import {Observable} from "rxjs";
import {ApiResponse} from "../model/api.response";
import {CompanyModel} from "../model/company.model";
import {CompanyPageModel} from "../model/company-page.model";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private fetchCompanies = '/api/company/companies';
  private upsert = '/api/company/upsert';
  private delete = '/api/company/delete/';
  private upsertPage = '/api/company/upsertPage';
  private deletePage = '/api/company/deletePage';

  constructor(private http: HttpClient,private _globalService: GlobalService) {
  }

  public getCompanies() : Observable<ApiResponse> {
    return this.http.get<ApiResponse>( this.fetchCompanies );
  }

  public upsertCompany( companyModel : CompanyModel): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.upsert, companyModel);
  }

  public deleteCompаny( companyId : number): Observable<ApiResponse>{
    return this.http.post<ApiResponse>(this.delete, companyId);
  }

  public upsertCompanyPage( companyPageModel : CompanyPageModel): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.upsertPage, companyPageModel);
  }

  public deleteCompаnyPage( pageId : number): Observable<ApiResponse>{
    return this.http.post<ApiResponse>(this.deletePage, pageId);
  }

}
