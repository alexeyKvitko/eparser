import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {GlobalService} from "./global.service";
import {Observable} from "rxjs";
import {ApiResponse} from "../model/api.response";
import {CompanyModel} from "../model/company.model";
import {PageTagModel} from "../model/page-tag.model";

@Injectable({
  providedIn: 'root'
})
export class PageTagService {

  private fetchTags = '/api/page/allTags';
  private upsert = '/api/page/upsertTag';
  private delete = '/api/page/deleteTag';

  constructor(private http: HttpClient,private _globalService: GlobalService) {
  }

  public getPageTags( pageId: number) : Observable<ApiResponse> {
    return this.http.get<ApiResponse>( this.fetchTags+"?pageId="+pageId );
  }

  public upsertTag( pageTagModel : PageTagModel): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.upsert, pageTagModel);
  }

  public deleteTag( tagId : number): Observable<ApiResponse>{
    return this.http.post<ApiResponse>(this.delete, tagId);
  }

}
