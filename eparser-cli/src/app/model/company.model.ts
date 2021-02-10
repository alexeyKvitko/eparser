import {CompanyPageModel} from "./company-page.model";

export class CompanyModel {
  id: number;
  companyName: string;
  thumb: string;
  url: string;
  country :string;
  language :string;
  companyPages: CompanyPageModel[];
}
