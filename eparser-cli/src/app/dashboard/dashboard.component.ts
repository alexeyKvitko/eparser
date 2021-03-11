import { Component, OnInit } from '@angular/core';
import {GlobalService} from "../services/global.service";
import {Router} from "@angular/router";
import {CompanyModel} from "../model/company.model";
import {CompanyPageModel} from "../model/company-page.model";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  company: CompanyModel;

  isEmptyScreen : boolean;

  constructor( private _globalService: GlobalService, private router: Router) {

  }

  ngOnInit() {
    if ( this._globalService.getSelectedCompany() == null ){
      this.isEmptyScreen = true;
    } else {
      this.company = this._globalService.getSelectedCompany();
      this.isEmptyScreen = false;
    }
    this._globalService.data$.subscribe(data => {
      if ( this._globalService.UPDATE_PAGES === data.ev  ) {
        if ( data.value != null ){
          this.company = data.value;
          this.isEmptyScreen = false;
        } else {
          this.isEmptyScreen = true;
        }
      }
    }, error => {
      console.log('Error: ' + error);
    });
  }

  editPage( companyPage: CompanyPageModel ){
    this._globalService.setShareObject( companyPage );
    this._globalService.dataBusChanged( this._globalService.LEFT_PANEL_SHOW,'false' );
    let route = 'main/companyPage';
    this._globalService.setCurrentRoute( route );
    this.router.navigate([ route ]);
  }

  addCompanyPage(){
    let companyPageModel = new CompanyPageModel();
    companyPageModel.companyId = this.company.id;
    companyPageModel.pageType = this._globalService.getPageType();
    this.editPage( companyPageModel );
  }

  parseConfig( companyPage: CompanyPageModel ): void{
    this._globalService.setShareObject( companyPage );
    this._globalService.dataBusChanged( this._globalService.LEFT_PANEL_SHOW,'false' );
    let route = 'main/parseConfig';
    this._globalService.setCurrentRoute( route );
    this.router.navigate([ route ]);
  }

}
