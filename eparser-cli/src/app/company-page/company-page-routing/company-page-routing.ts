import { NgModule } from '@angular/core';
import { Routes, RouterModule} from '@angular/router';
import {CompanyPageComponent} from "../company-page.component";
const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: CompanyPageComponent,
    data: {
      title: 'Company Component'
    }
  }
];
@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class CompanyPageRoutingModule { }
