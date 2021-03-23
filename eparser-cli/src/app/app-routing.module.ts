import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {LeftNavTemplateComponent} from "./template/left-nav-template.component";

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
     component: LoginComponent,
  },
  {
    path: 'main',
    component: LeftNavTemplateComponent,
    data: {
      title: 'OUTLET EXPRESS'
    },
    children: [
      {
        path: 'dashboard',
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule),
        data: {
          title: 'Панель Администратора'
        }
      },
      {
        path: 'company',
        loadChildren: () => import('./company/company.module').then(m => m.CompanyModule),
        data: {
          title: 'Компания'
        }
      },
      {
        path: 'companyPage',
        loadChildren: () => import('./parsing-page/parsing-page.module').then(m => m.ParsingPageModule),
        data: {
          title: 'Производители'
        }
      },
      {
        path: 'parseConfig',
        loadChildren: () => import('./parse-config/parse-config.module').then(m => m.ParseConfigModule),
        data: {
          title: 'Настройка парсинга'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
