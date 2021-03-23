import { Component, OnInit } from '@angular/core';
import {ParsingPageModel} from "../model/parsing-page.model";
import {GlobalService} from "../services/global.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {CompanyService} from "../services/company.service";


@Component({
  selector: 'app-company-page',
  templateUrl: './parsing-page.component.html',
  styleUrls: ['./parsing-page.component.scss']
})
export class ParsingPageComponent implements OnInit {

  parsingPage: ParsingPageModel;
  pageForm: FormGroup;
  pageError: string;
  isDeleteMsgShow: boolean;

  constructor(private _globalService: GlobalService, private formBuilder: FormBuilder, private router: Router, private companyService : CompanyService) { }

  ngOnInit(): void {
    this.isDeleteMsgShow = false;
    this.parsingPage = this._globalService.getShareObject();
    this.pageForm = this.formBuilder.group({
      id: this.parsingPage.id,
      pageName: this.parsingPage.pageName,
      pageUrl:  this.parsingPage.parseUrl,
      prefixUrl:  this.parsingPage.prefixUrl,
      attr: this.parsingPage.pageAttr,
      count: this.parsingPage.pageCount,
      tagTrash: this.parsingPage.tagLessInfo,
      tagSecStart: this.parsingPage.tagSectionStart
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
    this.companyService.deleteCompаnyPage( this.parsingPage.id ).subscribe(data => {
      if (data.status === 200) {
        this._globalService.dataBusChanged( this._globalService.UPDATE_COMPANIES,'true' );
        this.onCancel();
      } else {
        alert(data.message+this.parsingPage.pageName);
      }
    });
  }

  getChangedCompanyPage() : ParsingPageModel | null{
    let editedPage = new ParsingPageModel();
    editedPage.id = this.pageForm.get('id')?.value;
    editedPage.pageName = this.pageForm.get('pageName')?.value;
    editedPage.parseUrl = this.pageForm.get('pageUrl')?.value;
    editedPage.prefixUrl = this.pageForm.get('prefixUrl')?.value;
    editedPage.pageAttr = this.pageForm.get('attr')?.value;
    editedPage.pageCount = this.pageForm.get('count')?.value;
    editedPage.tagLessInfo = this.pageForm.get('tagTrash')?.value;
    editedPage.tagSectionStart = this.pageForm.get('tagSecStart')?.value;
    editedPage.pageType = this.parsingPage.pageType;
    editedPage.linkId= this.parsingPage.linkId;
    var notChanged = this.parsingPage.id == editedPage.id
      && this.parsingPage.pageName == editedPage.pageName
      && this.parsingPage.parseUrl == editedPage.parseUrl
      && this.parsingPage.pageAttr == editedPage.pageAttr
      && this.parsingPage.pageCount == editedPage.pageCount
      && this.parsingPage.tagLessInfo == editedPage.tagLessInfo
      && this.parsingPage.tagSectionStart == editedPage.tagSectionStart;

    return notChanged ? null : editedPage;
  }

}
