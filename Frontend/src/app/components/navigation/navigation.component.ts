import {Component} from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {RouterLink, RouterLinkActive} from "@angular/router";
import {NavLink} from "../../model/navigation";
import {ProtectionLevel} from "../../model/auth";
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [
    TranslateModule,
    RouterLink,
    NgOptimizedImage,
    RouterLinkActive
  ],
  templateUrl: './navigation.component.html',
})
export class NavigationComponent {
  public readonly navLinks: NavLink[] = [
    {
      name: 'link.home',
      link: '/',
      protection: ProtectionLevel.UNAUTHORIZED
    },
    {
      name: 'link.menu',
      link: '/menu',
      protection: ProtectionLevel.NONE
    },
    {
      name: 'link.login',
      link: '/auth/login',
      protection: ProtectionLevel.UNAUTHORIZED
    },
    {
      name: 'link.register',
      link: '/auth/register',
      protection: ProtectionLevel.UNAUTHORIZED
    }
  ]
}
