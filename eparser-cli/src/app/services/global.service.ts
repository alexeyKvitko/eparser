import {Injectable} from "@angular/core";
import {Subject} from "rxjs";
import {CompanyModel} from "../model/company.model";
import {EPBootstrapModel} from "../model/epbootstrap.model";

@Injectable()
export class GlobalService {

  public LEFT_PANEL_SHOW: string = "left_panel_show";
  public UPDATE_COMPANIES: string = "update_companies";
  public UPDATE_NAV_TITLE: string = "update_nav_title";
  public UPDATE_PAGES: string = "update_pages";

  private _currentRoute: string;
  private _shareObject: any;
  private _selectedCompany: CompanyModel;
  private _pageType: string;
  private _bootstrapModel: EPBootstrapModel;

  constructor(  ) {}

  private dataSource = new Subject<DataSourceClass>();

  data$ = this.dataSource.asObservable();

  public dataBusChanged(ev, value) {
    this.dataSource.next({
      ev: ev,
      value: value
    })
  }

  public getCurrentRoute(): string {
    return this._currentRoute;
  }

  public setCurrentRoute(value: string) {
    this._currentRoute = value;
  }


  public getShareObject(): any {
    return this._shareObject;
  }

  public setShareObject(value: any) {
    this._shareObject = value;
  }


  getSelectedCompany(): CompanyModel {
    return this._selectedCompany;
  }

  setSelectedCompany(value: CompanyModel) {
    this._selectedCompany = value;
  }


  getPageType(): string {
    return this._pageType;
  }

  setPageType(value: string) {
    this._pageType = value;
  }


  getBootstrapModel(): EPBootstrapModel {
    return this._bootstrapModel;
  }

  setBootstrapModel(value: EPBootstrapModel) {
    this._bootstrapModel = value;
  }
}

export class DataSourceClass {
  ev: string;
  value: any
}
