import { Component, OnInit } from '@angular/core';
import {GlobalService} from "../services/global.service";
import {Router} from "@angular/router";
import {ParsingPageModel} from "../model/parsing-page.model";
import {PageTypeConst} from "../model/page-type.const";
import {ParseService} from "../services/parse.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  parsingPages: ParsingPageModel[];
  linkId: number;
  pageTitle: string;

  isEmptyScreen : boolean;

  constructor( private _globalService: GlobalService, private parseService: ParseService, private router: Router) {

  }

  ngOnInit() {
    this.linkId = 1;
    if ( this._globalService.getSelectedCompany() == null ){
      this.isEmptyScreen = true;
    } else {
      this.parsingPages = this._globalService.getSelectedCompany().companyPages;
      this.isEmptyScreen = false;
    }
    this._globalService.data$.subscribe(data => {
      if ( this._globalService.UPDATE_PAGES === data.ev  ) {
        if ( data.value != null ){
          if ( PageTypeConst.PAGE_MANUFACTURER ==  this._globalService.getPageType() ){
            this.parsingPages = data.value.companyPages;
            this.pageTitle="";
          } else if (PageTypeConst.PAGE_CATEGORY ==  this._globalService.getPageType() ){
             this.linkId = data.value.categoryId;
             this.pageTitle = data.value.categoryName;
             this.loadParsingPages();
          }
          this.isEmptyScreen = false;
        } else {
          alert("Set empty");
          this.isEmptyScreen = true;
        }
      }
    }, error => {
      console.log('Error: ' + error);
    });
  }

  loadParsingPages(){
    this.parseService.getCategoryPages( this.linkId ).subscribe( data =>{
      if (data.status === 200) {
        this.parsingPages = data.result;
      } else {
        alert(data.message);
      }
    });
  }

  editPage( parsingPage: ParsingPageModel ){
    this._globalService.setShareObject( parsingPage );
    this._globalService.dataBusChanged( this._globalService.LEFT_PANEL_SHOW,'false' );
    let route = 'main/companyPage';
    this._globalService.setCurrentRoute( route );
    this.router.navigate([ route ]);
  }

  addParsingPage(){
    let parsingPageModel = new ParsingPageModel();
    parsingPageModel.linkId = this.linkId;
    parsingPageModel.pageType = this._globalService.getPageType();
    this.editPage( parsingPageModel );
  }

  parseConfig( companyPage: ParsingPageModel ): void{
    this._globalService.setShareObject( companyPage );
    this._globalService.dataBusChanged( this._globalService.LEFT_PANEL_SHOW,'false' );
    let route = 'main/parseConfig';
    this._globalService.setCurrentRoute( route );
    this.router.navigate([ route ]);
  }

}
