import {Injectable} from "@angular/core";
import {Subject} from "rxjs";

@Injectable()
export class GlobalService {

  public HEADER_TITLE: string = "header_title";

  private _currentRoute: string;

  private _shareObject: any;

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
}

export class DataSourceClass {
  ev: string;
  value: any
}
