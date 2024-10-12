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
      order: 1,
      name: 'link.home',
      link: '/',
      protection: ProtectionLevel.UNAUTHORIZED
    },
    {
      order: 2,
      name: 'link.menu',
      link: '/menu',
      protection: ProtectionLevel.NONE
    },
    {
      order: 3,
      name: 'link.login',
      link: '/login',
      protection: ProtectionLevel.UNAUTHORIZED
    },
    {
      order: 4,
      name: 'link.register',
      link: '/register',
      protection: ProtectionLevel.UNAUTHORIZED
    },
    {
      order: 1,
      name: 'link.overview',
      link: '/overview',
      protection: ProtectionLevel.AUTHORIZED
    },
    {
      order: 3,
      name: 'link.reserve',
      link: '/reserve',
      protection: ProtectionLevel.AUTHORIZED
    },
    {
      order: 4,
      name: 'link.dashboard',
      link: '/dashboard',
      protection: ProtectionLevel.AUTHORIZED,
      accountType: ProfileType.WAITER
    },
    {
      order: 5,
      name: 'link.restaurant',
      link: '/restaurant',
      protection: ProtectionLevel.AUTHORIZED,
      accountType: ProfileType.ADMIN
    }
  ];

  public navLinksListener: Observable<NavLink[]> = this.loggedInService.userInfo
    .pipe(
      switchMap(userInfo =>
        of(
          this.navLinks.filter(navLink => {
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
          }).sort((a, b) => a.order - b.order)
        ))
    )

  constructor(private readonly loggedInService: LoggedInService) {
  }
}
