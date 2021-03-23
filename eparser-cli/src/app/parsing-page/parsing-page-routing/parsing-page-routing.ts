import { NgModule } from '@angular/core';
import { Routes, RouterModule} from '@angular/router';
import {ParsingPageComponent} from "../parsing-page.component";
const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: ParsingPageComponent,
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
