import { NgModule } from '@angular/core';
import { Routes, RouterModule} from '@angular/router';
import {ParseConfigComponent} from "../parse-config.component";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: ParseConfigComponent,
    data: {
      title: 'Parse Config Component'
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
export class ParseConfigRoutingModule { }
