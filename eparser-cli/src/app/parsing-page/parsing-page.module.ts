import { NgModule } from '@angular/core';
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared/shared.module";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {CompanyPageRoutingModule} from "./parsing-page-routing/parsing-page-routing";
import {ParsingPageComponent} from "./parsing-page.component";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    CompanyPageRoutingModule
  ],
  declarations: [ ParsingPageComponent],
  providers: []
})
export class ParsingPageModule { }
