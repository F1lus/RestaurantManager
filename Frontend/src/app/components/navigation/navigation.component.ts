import {Component} from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [
    TranslateModule,
    RouterLink
  ],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.scss'
})
export class NavigationComponent {
  public readonly navLinks = [
    {
      name: 'general.home',
      link: '/'
    },
    {
      name: 'general.menu',
      link: '/menu'
    }
  ]
}
