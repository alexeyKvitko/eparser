import { NgModule } from '@angular/core';
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared/shared.module";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {ParseConfigRoutingModule} from "./parse-config-routing/parse-config-routing";
import {ParseConfigComponent} from "./parse-config.component";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    ParseConfigRoutingModule
  ],
  declarations: [ ParseConfigComponent],
  providers: []
})
export class ParseConfigModule { }
