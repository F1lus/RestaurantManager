import {Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {MenuComponent} from "./pages/menu/menu.component";
import {LoginComponent} from "./pages/auth/login/login.component";
import {RegisterComponent} from "./pages/auth/register/register.component";
import {menuResolver} from "./resolvers/menu.resolver";
import {isNotLoggedInGuard} from "./guards/is-not-logged-in.guard";
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {loggedInGuard} from "./guards/logged-in.guard";

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
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
    canActivate: [loggedInGuard]
  }
];
