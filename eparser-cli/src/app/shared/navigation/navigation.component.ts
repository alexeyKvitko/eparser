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

   showDictMenu: boolean;
   showLogMenu: boolean;
   logSrc: string = "assets/images/icons/";
    companies : CompanyModel[];

  constructor(private _globalService: GlobalService, private router: Router, private companyService: CompanyService) {
  }

  ngOnInit() {
    this.showDictMenu = false;
    this.showLogMenu = false;
    this.companyService.getCompanies().subscribe(data => {
      if (data.status === 200) {
        this.companies = data.result;
      } else {
        alert(data.message);
      }
    });
  }



  showCompanyDetails(company: CompanyModel) {
    this._globalService.setShareObject( company );
    let route = 'main/company';
    this._globalService.setCurrentRoute( route );
    this.router.navigate([ route ]);
  }

  showLogSubMenu() {
    this.showLogMenu = !this.showLogMenu;
  }

  storeDashboard() {
    this._globalService.setCurrentRoute('main/dashboard');
  }

  storeRegionInfo() {
    this._globalService.setCurrentRoute('main/region-review');
  }


}
