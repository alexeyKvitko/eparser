import { Component, OnInit } from '@angular/core';
import {GlobalService} from "../services/global.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {



  constructor( private _globalService: GlobalService, private router: Router) {}

  ngOnInit() {

  }



}
