import {Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {MenuComponent} from "./pages/menu/menu.component";
import {LoginComponent} from "./pages/auth/login/login.component";
import {RegisterComponent} from "./pages/auth/register/register.component";
import {menuResolver} from "./resolvers/menu.resolver";
import {isNotLoggedInGuard} from "./guards/is-not-logged-in.guard";
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {loggedInGuard} from "./guards/logged-in.guard";
import {OverviewComponent} from "./pages/overview/overview.component";
import {ReserveComponent} from "./pages/reserve/reserve.component";
import {hasPermission} from "./guards/has-permission.guard";
import {ProfileType} from "./model/auth";
import {profilesResolver} from "./resolvers/profiles.resolver";
import {allergensResolver} from "./resolvers/allergens.resolver";
import {seatsResolver} from "./resolvers/seats.resolver";
import {reservationResolver} from "./resolvers/reservation.resolver";
import {LogoutComponent} from "./pages/logout/logout.component";

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    canActivate: [isNotLoggedInGuard],
    component: HomeComponent,
  },
  {
    path: 'menu',
    component: MenuComponent,
    resolve: {menu: menuResolver},
    runGuardsAndResolvers: "paramsOrQueryParamsChange"
  },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [isNotLoggedInGuard]
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [isNotLoggedInGuard]
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    resolve: {profiles: profilesResolver, menu: menuResolver, allergens: allergensResolver, seats: seatsResolver},
    runGuardsAndResolvers: "paramsOrQueryParamsChange",
    canActivate: [loggedInGuard, hasPermission],
    data: {permission: ProfileType.ADMIN}
  },
  {
    path: 'overview',
    component: OverviewComponent,
    resolve: {reservations: reservationResolver, menu: menuResolver, seats: seatsResolver},
    canActivate: [loggedInGuard],
  },
  {
    path: 'reserve',
    component: ReserveComponent,
    resolve: {menu: menuResolver, seats: seatsResolver},
    canActivate: [loggedInGuard]
  },
  {
    path: 'logout',
    component: LogoutComponent,
    canActivate: [loggedInGuard]
  }
];
