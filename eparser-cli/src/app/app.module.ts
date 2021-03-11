import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import {AppRoutingModule, routes} from './app-routing.module';
import { AppComponent } from './app.component';
import {HeaderComponent} from "./shared/header/header.component";
import {LeftNavTemplateComponent} from "./template/left-nav-template.component";
import {NavigationComponent} from "./shared/navigation/navigation.component";
import {BlankTemplateComponent} from "./template/blank-template.component";
import {LoginService} from "./services/login.service";
import {AuthService} from "./services/auth.service";
import {TokenInterceptor} from "./services/token-interceptor.service";
import {GlobalService} from "./services/global.service";
import { LoginComponent } from './login/login.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {RouterModule} from "@angular/router";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {CompanyService} from "./services/company.service";
import {PageTagService} from "./services/page-tag.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    BlankTemplateComponent,
    HeaderComponent,
    LeftNavTemplateComponent,
    NavigationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes, {useHash: true}),
    NgbModule
  ],
  providers: [
    LoginService,
    AuthService,
    GlobalService,
    CompanyService,
    PageTagService,
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
