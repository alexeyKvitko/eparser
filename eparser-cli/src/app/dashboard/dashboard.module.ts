import { NgModule } from '@angular/core';
import {CommonModule} from "@angular/common";
import { DashboardComponent } from './dashboard.component';
import { DashboardRoutingModule } from './dashboard-routing/dashboard-routing.module';
import {SharedModule} from "../shared/shared.module";

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    DashboardRoutingModule
  ],
  declarations: [ DashboardComponent],
  providers: []
})
export class DashboardModule { }
