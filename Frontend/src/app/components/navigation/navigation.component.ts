import {Component, OnInit} from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {RouterLink, RouterLinkActive} from "@angular/router";
import {NavLink} from "../../model/navigation";
import {AsyncPipe, NgOptimizedImage} from "@angular/common";
import {NavigationService} from "../../services/navigation.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [
    TranslateModule,
    RouterLink,
    NgOptimizedImage,
    RouterLinkActive,
    AsyncPipe
  ],
  templateUrl: './navigation.component.html',
})
export class NavigationComponent implements OnInit {

  public navLinks!: Observable<NavLink[]>;

  constructor(private readonly navigationService: NavigationService) {
  }

  public ngOnInit(): void {
    this.navLinks = this.navigationService.navLinksListener;
  }

}
