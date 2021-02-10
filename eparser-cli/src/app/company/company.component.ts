import { Component, OnInit } from '@angular/core';
import {CompanyModel} from "../model/company.model";
import {GlobalService} from "../services/global.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {CompanyService} from "../services/company.service";


@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {

  company: CompanyModel;
  companyForm: FormGroup;
  companyError: string;
  isDeleteMsgShow: boolean;

  constructor( private _globalService: GlobalService, private formBuilder: FormBuilder, private router: Router, private companyService : CompanyService) { }

  ngOnInit(): void {
    this.isDeleteMsgShow = false;
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
    let changedCompany =  this.getChangedCompany();
    if ( changedCompany != null ){
     this.companyService.upsertCompany( changedCompany ).subscribe(data => {
       if (data.status === 200) {
         this._globalService.dataBusChanged( this._globalService.UPDATE_COMPANIES,'true' );
         this.onCancel();
       } else {
         alert(data.message);
       }
     });
    }
  }

  onCancel(): void{
    this._globalService.dataBusChanged( this._globalService.LEFT_PANEL_SHOW,'true' );
    this.router.navigate(['main/dashboard']);
  }

   getChangedCompany() : CompanyModel | null{
    let editedCompany = new CompanyModel();
    editedCompany.id = this.companyForm.get('id')?.value;
    editedCompany.companyName = this.companyForm.get('editName')?.value;
    editedCompany.url = this.companyForm.get('editUrl')?.value;
    editedCompany.thumb = this.companyForm.get('editThumb')?.value;
    editedCompany.country = this.companyForm.get('editCountry')?.value;
    editedCompany.language =  this.companyForm.get('editLanguage')?.value;
    var notChanged = this.company.id == editedCompany.id
                    && this.company.companyName == editedCompany.companyName
                    && this.company.url == editedCompany.url
                    && this.company.thumb == editedCompany.thumb
                    && this.company.country == editedCompany.country
                    && this.company.language == editedCompany.language
    return notChanged ? null : editedCompany;
  }

 onDelete(): void{
   this.companyService.deleteCompаny( this.company.id ).subscribe(data => {
     if (data.status === 200) {
       this._globalService.dataBusChanged( this._globalService.UPDATE_COMPANIES,'true' );
       this.onCancel();
     } else {
       alert(data.message+this.company.companyName);
     }
   });
 }

}
