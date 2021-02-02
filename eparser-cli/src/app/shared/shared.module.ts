import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";



/* components */

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule
  ],
  declarations: [

  ],
  exports: [
    ]
})
export class SharedModule { }
