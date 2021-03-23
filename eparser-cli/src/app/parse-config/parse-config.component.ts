import {Component, OnInit} from '@angular/core';
import {GlobalService} from "../services/global.service";
import {PageTagModel} from "../model/page-tag.model";
import {PageTagService} from "../services/page-tag.service";
import {ParsingPageModel} from "../model/parsing-page.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {PairModel} from "../model/pair.model";
import {Router} from "@angular/router";
import {ParseService} from "../services/parse.service";
import {TestResultModel} from "../model/test-result.model";
import {ParseItemModel} from "../model/parse-item.model";
import {BlockModel} from "../model/block.model";

@Component({
  selector: 'app-parse-config',
  templateUrl: './parse-config.component.html',
  styleUrls: ['./parse-config.component.scss']
})
export class ParseConfigComponent implements OnInit {

  directions: PairModel[] = [
    {key: 'Вперед', value: 1},
    {key: 'Точно', value: 0},
    {key: 'Назад', value: -1},
  ];

  companyPage: ParsingPageModel;
  pageTags: PageTagModel[] | null;
  blocks: BlockModel[] | null;
  selPageTag: PageTagModel;
  showPageTagForm: boolean;
  showParseResult: boolean;
  addEnabled: boolean;
  tagForm: FormGroup;
  tagError: string;
  isDeleteMsgShow: boolean;
  selDirection: number;
  testResult: TestResultModel;
  busy: boolean;

  constructor(private _globalService: GlobalService, private pageTagService: PageTagService, private formBuilder: FormBuilder,
              private router: Router, private parseService: ParseService) {
  }

  ngOnInit(): void {
    this.companyPage = this._globalService.getShareObject();
    this._globalService.dataBusChanged( this._globalService.UPDATE_NAV_TITLE, "Настройка парсинга");
    this.showPageTagForm = false;
    this.showParseResult = false;
    this.selDirection = 1;
    this.isDeleteMsgShow = false;
    this.busy = false;
    this.addEnabled = false;
    this.loadPageTags();
  }

  addPageTag() {
    let newPageTag = new PageTagModel();
    newPageTag.pageId = this.companyPage.id;
    newPageTag.reversed = 1;
    newPageTag.entryNumber = 1;
    this.showTagForm( newPageTag );
  }

  loadPageTags() {
    this.busy = true;
    this.pageTagService.getPagedData(this.companyPage.id).subscribe(data => {
      if (data.status === 200) {
        this.pageTags = data.result.pageTags;
        console.log("updated tags:", this.pageTags);
        this.blocks = data.result.blocks;
      } else {
        alert(data.message);
      }
      this.busy = false;
    });
  }

  showTagForm(pageTag: PageTagModel): void {
    this.selPageTag = pageTag;
    console.log('Page tag', this.selPageTag );
    this.tagForm = this.formBuilder.group({
      id: pageTag.id,
      fDescription: pageTag.tagName,
      fStartTag: pageTag.startTag,
      fEndtTag: pageTag.endTag,
      fDirection: pageTag.reversed,
      fEntryNum: pageTag.entryNumber,
      fNeedTranslate: pageTag.needTranslate,
      fIsImage: pageTag.isImage,
      fInnerSearch: pageTag.innerSearch,
      fMapTable: pageTag.mapTable,
      fMapField: pageTag.mapField

    });
    this.showPageTagForm = true;
    this.tagForm.get('fDescription')?.disable();
  }

  onSubmit() {
    let changedTag = this.getChangedTag();
    if ( changedTag != null ){
      this.pageTagService.upsertTag( changedTag ).subscribe(data => {
        if (data.status === 200) {
          this.loadPageTags();
          this.onCancel();
        } else {
          alert(data.message);
        }
      });
    }
  }

  getChangedTag(): PageTagModel | null {
    let submitTag = new PageTagModel();
    submitTag.id = this.tagForm.get('id')?.value;
    submitTag.pageId = this.selPageTag.pageId;
    submitTag.tagName = this.tagForm.get('fDescription')?.value;
    submitTag.startTag = this.tagForm.get('fStartTag')?.value;
    submitTag.endTag = this.tagForm.get('fEndtTag')?.value;
    submitTag.reversed = this.tagForm.get('fDirection')?.value;
    submitTag.entryNumber = this.tagForm.get('fEntryNum')?.value;
    submitTag.isImage = this.tagForm.get('fIsImage')?.value == true ? 1 : 0;
    submitTag.needTranslate = this.tagForm.get('fNeedTranslate')?.value == true ? 1 : 0;
    submitTag.innerSearch = this.tagForm.get('fInnerSearch')?.value == true ? 1 : 0;
    submitTag.mapTable = this.tagForm.get('fMapTable')?.value;
    submitTag.mapField = this.tagForm.get('fMapField')?.value;
    var notChanged =  submitTag.id == this.selPageTag.id
                      && submitTag.tagName == this.selPageTag.tagName
                      && submitTag.startTag == this.selPageTag.startTag
                      && submitTag.endTag == this.selPageTag.endTag
                      && submitTag.reversed == this.selPageTag.reversed
                      && submitTag.entryNumber == this.selPageTag.entryNumber
                      && submitTag.needTranslate == this.selPageTag.needTranslate
                      && submitTag.isImage == this.selPageTag.isImage
                      && submitTag.innerSearch == this.selPageTag.innerSearch
                      && submitTag.mapTable == this.selPageTag.mapTable
                      && submitTag.mapField == this.selPageTag.mapField;
    return notChanged ? null : submitTag;
  }

  onCancel() {
    this.showPageTagForm = false;
  }

  onDelete() {
    this.pageTagService.deleteTag( this.selPageTag.id ).subscribe(data => {
      if (data.status === 200) {
        this.loadPageTags();
        this.onCancel();
      } else {
        alert(data.message+this.companyPage.pageName);
      }
    });
  }

  testParsing(){
    this.busy = true;
    this.parseService.testParsing(this.companyPage.id).subscribe(data => {
      if (data.status === 200) {
        this.testResult = data.result;
        this.blocks = this.testResult.data;
        this.showParseResult = true;
        this.addEnabled = true;
      } else {
        alert(data.message);
      }
      this.busy = false;
    });
  }

  addToScheduller(){
    this.busy = true;
    this.parseService.addToScheduller(this.companyPage.id, this.companyPage.linkId).subscribe(data => {
      if (data.status === 200) {
        this.testResult = data.result;
        this.showParseResult = true;
        this.addEnabled = false;
      } else {
        alert(data.message);
      }
      this.busy = false;
    });
  }

  goBack(){
    this.router.navigate([ 'main/dashboard' ]);
  }



}
