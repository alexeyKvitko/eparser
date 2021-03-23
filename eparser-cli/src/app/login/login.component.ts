import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {GlobalService} from "../services/global.service";
import {Router} from "@angular/router";
import {LoginService} from "../services/login.service";
import {CompanyService} from "../services/company.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginError: string;
  busy: boolean;

  constructor(private _globalService: GlobalService, private router: Router, private loginService: LoginService,
              private companyService : CompanyService, private formBuilder: FormBuilder ) {
    this.busy = false;
  }

  onSubmit() {
    // for test ui elements
    // this.router.navigate(['main/testing-page']);
    // return;

    const loginPayload = {
      username: this.loginForm.controls.login.value,
      password: this.loginForm.controls.password.value
    };
    this.loginService.login(loginPayload).subscribe(data => {
      if (data.status === 200) {
        this.busy = false;
        window.localStorage.setItem('token', data.result.token);
        this.companyService.getBootstrapModel().subscribe( data=>{
          if ( data.status === 200 ){
            this._globalService.setBootstrapModel(data.result);
            console.log(data.result);
            this.router.navigate(['main/dashboard']);
          } else {
            alert("No data. "+ data.message);
          }
        })

      } else {
        window.localStorage.removeItem('token');
        this.loginError = "Логин или Пароль не найден";
      }
    });
  }

  initBootstrapModel() {
    this.busy = false;
    // this.bootstrapService.loadBootstrapModel().subscribe(data => {
    //   this.busy = false;
    //   if (data.status == 200) {
    //     this.bootstrapService.setBootstrapModel(<BootstrapModel>data.result);
    //     this._globalService.setCachedRegionModels( this.bootstrapService.getBootstrapModel().regions );
    //     this.router.navigate(['main/dashboard']);
    //   }
    // });
  }

  ngOnInit() {
    window.localStorage.removeItem('token');
    this.busy = false;
    this.loginError = "";
    this.loginForm = this.formBuilder.group({
      login: ['e_admin', Validators.required],
      password: ['e_pswd', Validators.compose([Validators.required])]
    });
    // this.onSubmit();
  }


}
