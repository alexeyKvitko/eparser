import { Component } from '@angular/core';
import {Title} from "@angular/platform-browser";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'eparser-cli';

  constructor(private titleService: Title, router: Router, activatedRoute: ActivatedRoute) {
    router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        const title = this.getTitle(router.routerState, router.routerState.root).join(' | ');
        titleService.setTitle(title);
      }
    });
  }

  getTitle(state: any, parent: any) {
    const data : any[] = [];
    if (parent && parent.snapshot.data && parent.snapshot.data.title) {
      data.push(parent.snapshot.data.title);
    }

    if (state && parent) {
      data.push(... this.getTitle(state, state.firstChild(parent)));
    }
    return data;
  }

  public setTitle(newTitle: string) {
    this.titleService.setTitle(newTitle);
  }

}
