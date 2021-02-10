import {Component, OnInit} from '@angular/core';
import {GlobalService} from "../../services/global.service";
import {Router} from "@angular/router";
import {CompanyModel} from "../../model/company.model";
import {CompanyService} from "../../services/company.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {

   showAvailSites: boolean;
   logSrc: string = "assets/images/icons/";
   companies : CompanyModel[];
   selectedCompany: CompanyModel|null;

  constructor(private _globalService: GlobalService, private router: Router, private companyService: CompanyService) {
  }

  ngOnInit() {
    this.showAvailSites = true;
    this.selectedCompany = null;
    this._globalService.data$.subscribe(data => {
      if ( this._globalService.LEFT_PANEL_SHOW === data.ev  ) {
        this.showAvailSites = data.value === 'true';
      }
      if ( this._globalService.UPDATE_COMPANIES === data.ev  &&
                                            data.value === 'true') {
        this.loadCompanies();
      }
    }, error => {
      console.log('Error: ' + error);
    });

    this.loadCompanies();
  }

  showCompanyDetails(company: CompanyModel) {
    this.showAvailSites = false;
    this.selectedCompany = company;
    this._globalService.setSelectedCompany( company );
    this._globalService.setShareObject( company );
    let route = 'main/company';
    this._globalService.setCurrentRoute( route );
    this.router.navigate([ route ]);
  }

  addCompany(){
    this.showCompanyDetails( new CompanyModel() );
  }

  loadCompanies() {
    this.companyService.getCompanies().subscribe(data => {
      if (data.status === 200) {
        this.companies = data.result;
        if ( this.selectedCompany != null ){
          this.companies.forEach( company => { if ( company.id == this.selectedCompany?.id){ this.showCompanyPages( company ) ;}})
        }
      } else {
        alert(data.message);
      }
    });
  }

  showCompanyPages( company: CompanyModel): void{
    this.selectedCompany = company;
    this._globalService.setSelectedCompany( company );
    this.showAvailSites = false;
    this._globalService.dataBusChanged( this._globalService.UPDATE_PAGES, company);
  }

  showAllCompanies(){
    this.showAvailSites = true;
    let route = 'main/dashboard';
    this._globalService.dataBusChanged( this._globalService.UPDATE_PAGES, null);
    this._globalService.setCurrentRoute( route );
    this.router.navigate([ route ]);
  }


}
