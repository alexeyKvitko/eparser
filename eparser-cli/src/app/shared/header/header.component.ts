import { Component, OnInit } from '@angular/core';
import {GlobalService} from "../../services/global.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  headerTitle: string;

  constructor(private _globalService: GlobalService) { }

  ngOnInit() {

  }

}
