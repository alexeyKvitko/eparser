import {Component, OnInit} from '@angular/core';
import {GlobalService} from "../../services/global.service";
import {Router} from "@angular/router";
import {CompanyModel} from "../../model/company.model";
import {CompanyService} from "../../services/company.service";
import {PageTypeConst} from "../../model/page-type.const";
import {CategoryModel} from "../../model/category.model";

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
   navTitle: string|null;
   categories: CategoryModel[];

  constructor(private _globalService: GlobalService, private router: Router, private companyService: CompanyService) {
  }

  ngOnInit() {
    this.showAvailSites = true;
    this.selectedCompany = null;
    this.navTitle = null;
    if ( this._globalService.getBootstrapModel() != null ){
      this.categories = this._globalService.getBootstrapModel().categories;
      console.log(this.categories);
    }
    this._globalService.data$.subscribe(data => {
      if ( this._globalService.LEFT_PANEL_SHOW === data.ev  ) {
        this.showAvailSites = data.value === 'true';
      }
      if ( this._globalService.UPDATE_COMPANIES === data.ev  &&
                                            data.value === 'true') {
        this.loadCompanies();
      }
      if ( this._globalService.UPDATE_NAV_TITLE === data.ev  ) {
        this.navTitle = data.value
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

  onCategorySelect( category: CategoryModel){
    if( category.node == false ){
      this.navTitle = category.categoryName;
      this._globalService.setPageType( PageTypeConst.PAGE_CATEGORY );
      this._globalService.dataBusChanged( this._globalService.UPDATE_PAGES, category );
    }
  }


  loadCompanies() {
    this.companyService.getCompanies().subscribe(data => {
      if (data.status === 200) {
        this.companies = data.result;
      } else {
        alert(data.message);
      }
    });
  }

  showManufacturerPages( ): void{
    this.selectedCompany = this.companies[0];
    this.navTitle = 'Производители';
    this._globalService.setPageType( PageTypeConst.PAGE_MANUFACTURER );
    this._globalService.setSelectedCompany( this.selectedCompany );
    this.showAvailSites = false;
    this._globalService.dataBusChanged( this._globalService.UPDATE_PAGES, this.selectedCompany);
  }

  showAllCompanies(){
    this.showAvailSites = true;
    let route = 'main/dashboard';
    this._globalService.setCurrentRoute( route );
    this.router.navigate([ route ]);
    this._globalService.dataBusChanged( this._globalService.UPDATE_PAGES, null);
  }


}
