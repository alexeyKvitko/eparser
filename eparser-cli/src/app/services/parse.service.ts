import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {GlobalService} from "./global.service";
import {Observable} from "rxjs";
import {ApiResponse} from "../model/api.response";
import {PageTagModel} from "../model/page-tag.model";

@Injectable({
  providedIn: 'root'
})
export class ParseService {

  private test = '/api/parse/test';
  private scheduler = '/api/parse/scheduler';
  private categoryPages = '/api/parse/getPagesByCategory';


  constructor(private http: HttpClient,private _globalService: GlobalService) {
  }

  public testParsing( pageId: number) : Observable<ApiResponse> {
    return this.http.get<ApiResponse>( this.test+"?pageId="+pageId );
  }

  public addToScheduller( pageId: number, categoryId: number) : Observable<ApiResponse> {
    return this.http.get<ApiResponse>( this.scheduler+"?pageId="+pageId+"&categoryId="+categoryId );
  }

  public getCategoryPages( categoryId: number) : Observable<ApiResponse> {
    return this.http.get<ApiResponse>( this.categoryPages+"?categoryId="+categoryId );
  }

}
