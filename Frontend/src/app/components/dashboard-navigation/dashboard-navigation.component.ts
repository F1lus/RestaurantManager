import {Component} from '@angular/core';
import {AsyncPipe} from "@angular/common";
import {RouterLink, RouterLinkActive} from "@angular/router";
import {TranslateModule} from "@ngx-translate/core";
import {DashboardState} from "../../model/common";

interface DashboardLink {
  name: string;
  queryParam: DashboardState;
}

@Component({
  selector: 'app-dashboard-navigation',
  standalone: true,
  imports: [
    AsyncPipe,
    RouterLinkActive,
    TranslateModule,
    RouterLink
  ],
  templateUrl: './dashboard-navigation.component.html',
  styleUrl: './dashboard-navigation.component.scss'
})
export class DashboardNavigationComponent {

  public links: DashboardLink[] = [
    {name: 'dashboard.link.manage_users', queryParam: DashboardState.MODIFY_USER},
    {name: 'dashboard.link.manage_seats', queryParam: DashboardState.MODIFY_SEAT},
    {name: 'dashboard.link.manage_menu', queryParam: DashboardState.MODIFY_MENU},
  ]

}
