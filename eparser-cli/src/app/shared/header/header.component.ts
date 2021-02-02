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
    this._globalService.data$.subscribe(data => {
      if (data.ev === this._globalService.HEADER_TITLE ) {
        this.headerTitle = data.value;
      }
    }, error => {
      console.log('Error: ' + error);
    });
  }

}
