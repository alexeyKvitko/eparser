import { Component, OnInit } from '@angular/core';
import {CompanyModel} from "../model/company.model";
import {GlobalService} from "../services/global.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {

  company: CompanyModel;
  companyForm: FormGroup;
  companyError: string;

  constructor( private _globalService: GlobalService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.company = this._globalService.getShareObject();
    this.companyForm = this.formBuilder.group({
      id: this.company.id,
      editName: this.company.companyName,
      editUrl:  this.company.url,
      editThumb: this.company.thumb,
      editCountry: this.company.country,
      editLanguage: this.company.language
    });
  }

  onSubmit(): void{

  }

}
