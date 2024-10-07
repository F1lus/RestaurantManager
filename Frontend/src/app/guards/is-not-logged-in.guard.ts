import {CanActivateFn} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {map, tap} from "rxjs";

export const isNotLoggedInGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);

  return authService.isLoggedIn()
    .pipe(
      map(value => !value),
      tap(console.log)
    );
};
