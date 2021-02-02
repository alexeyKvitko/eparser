import { NgModule } from '@angular/core';
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared/shared.module";
import {CompanyComponent} from "./company.component";
import {CompanyRoutingModule} from "./company-routing/company-routing.module";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    CompanyRoutingModule
  ],
  declarations: [ CompanyComponent],
  providers: []
})
export class CompanyModule { }
