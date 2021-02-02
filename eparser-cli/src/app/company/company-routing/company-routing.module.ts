import { NgModule } from '@angular/core';
import { Routes, RouterModule} from '@angular/router';
import {CompanyComponent} from "../company.component";
const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: CompanyComponent,
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
export class CompanyRoutingModule { }
