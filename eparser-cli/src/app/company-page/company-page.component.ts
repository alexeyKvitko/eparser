import { Component, OnInit } from '@angular/core';
import {CompanyPageModel} from "../model/company-page.model";
import {GlobalService} from "../services/global.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {CompanyService} from "../services/company.service";
import {CompanyModel} from "../model/company.model";

@Component({
  selector: 'app-company-page',
  templateUrl: './company-page.component.html',
  styleUrls: ['./company-page.component.scss']
})
export class CompanyPageComponent implements OnInit {

  companyPage: CompanyPageModel;
  pageForm: FormGroup;
  pageError: string;
  isDeleteMsgShow: boolean;

  constructor(private _globalService: GlobalService, private formBuilder: FormBuilder, private router: Router, private companyService : CompanyService) { }

  ngOnInit(): void {
    this.isDeleteMsgShow = false;
    this.companyPage = this._globalService.getShareObject();
    this.pageForm = this.formBuilder.group({
      id: this.companyPage.id,
      pageName: this.companyPage.pageName,
      pageUrl:  this.companyPage.parseUrl,
      attr: this.companyPage.pageAttr,
      count: this.companyPage.pageCount,
      tagTrash: this.companyPage.tagLessInfo,
      tagSecStart: this.companyPage.tagSectionStart
    });
  }

  onSubmit(){
    let changedPage =  this.getChangedCompanyPage();
    if ( changedPage != null ){
      this.companyService.upsertCompanyPage( changedPage ).subscribe(data => {
        if (data.status === 200) {
          this._globalService.dataBusChanged( this._globalService.UPDATE_COMPANIES,'true' );
          this.onCancel();
        } else {
          alert(data.message);
        }
      });
    }
  }

  onCancel(){
    this._globalService.dataBusChanged( this._globalService.LEFT_PANEL_SHOW,'true' );
    this._globalService.dataBusChanged( this._globalService.UPDATE_COMPANIES,'true' );
    this.router.navigate(['main/dashboard']);
  }

  onDelete(){
    this.companyService.deleteCompаnyPage( this.companyPage.id ).subscribe(data => {
      if (data.status === 200) {
        this._globalService.dataBusChanged( this._globalService.UPDATE_COMPANIES,'true' );
        this.onCancel();
      } else {
        alert(data.message+this.companyPage.pageName);
      }
    });
  }

  getChangedCompanyPage() : CompanyPageModel | null{
    let editedPage = new CompanyPageModel();
    editedPage.id = this.pageForm.get('id')?.value;
    editedPage.pageName = this.pageForm.get('pageName')?.value;
    editedPage.parseUrl = this.pageForm.get('pageUrl')?.value;
    editedPage.pageAttr = this.pageForm.get('attr')?.value;
    editedPage.pageCount = this.pageForm.get('count')?.value;
    editedPage.tagLessInfo = this.pageForm.get('tagTrash')?.value;
    editedPage.tagSectionStart = this.pageForm.get('tagSecStart')?.value;
    editedPage.pageType = this.companyPage.pageType;
    editedPage.companyId= this.companyPage.companyId;
    var notChanged = this.companyPage.id == editedPage.id
      && this.companyPage.pageName == editedPage.pageName
      && this.companyPage.parseUrl == editedPage.parseUrl
      && this.companyPage.pageAttr == editedPage.pageAttr
      && this.companyPage.pageCount == editedPage.pageCount
      && this.companyPage.tagLessInfo == editedPage.tagLessInfo
      && this.companyPage.tagSectionStart == editedPage.tagSectionStart;

    return notChanged ? null : editedPage;
  }

}
