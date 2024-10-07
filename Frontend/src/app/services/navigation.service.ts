import {Injectable} from '@angular/core';
import {NavLink} from "../model/navigation";
import {ProfileType, ProtectionLevel} from "../model/auth";
import {LoggedInService} from "./logged-in.service";
import {Observable, of, switchMap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NavigationService {

  private readonly navLinks: NavLink[] = [
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
      link: '/login',
      protection: ProtectionLevel.UNAUTHORIZED
    },
    {
      name: 'link.register',
      link: '/register',
      protection: ProtectionLevel.UNAUTHORIZED
    },
    {
      name: 'link.overview',
      link: '/overview',
      protection: ProtectionLevel.AUTHORIZED
    },
    {
      name: 'link.reserve',
      link: '/reserve',
      protection: ProtectionLevel.AUTHORIZED
    },
    {
      name: 'link.dashboard',
      link: '/dashboard',
      protection: ProtectionLevel.AUTHORIZED,
      accountType: ProfileType.WAITER
    },
    {
      name: 'link.restaurant',
      link: '/restaurant',
      protection: ProtectionLevel.AUTHORIZED,
      accountType: ProfileType.ADMIN
    }
  ];

  public navLinksListener: Observable<NavLink[]> = this.loggedInService.userInfo
    .pipe(
      switchMap(userInfo => {
        return of(this.navLinks.filter(navLink => {
          if (navLink.protection === ProtectionLevel.NONE) {
            return true;
          }

          if (navLink.protection === ProtectionLevel.UNAUTHORIZED && !userInfo) {
            return true;
          }

          if (navLink.protection === ProtectionLevel.AUTHORIZED && !!userInfo) {
            if (navLink.accountType) {
              return userInfo.profileTypes.includes(navLink.accountType)
            }
            return true;
          }

          return false;
        }))
      })
    )

  constructor(private readonly loggedInService: LoggedInService) {
  }
}
