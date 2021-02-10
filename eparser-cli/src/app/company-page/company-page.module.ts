import { NgModule } from '@angular/core';
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared/shared.module";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {CompanyPageRoutingModule} from "./company-page-routing/company-page-routing";
import {CompanyPageComponent} from "./company-page.component";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    CompanyPageRoutingModule
  ],
  declarations: [ CompanyPageComponent],
  providers: []
})
export class CompanyPageModule { }
