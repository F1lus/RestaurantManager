import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {of, switchMap} from "rxjs";
import {fromPromise} from "rxjs/internal/observable/innerFrom";

export const loggedInGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);

  return authService.isLoggedIn().pipe(
    switchMap(isLoggedIn => !isLoggedIn ? fromPromise(router.navigate(['login'])) : of(true))
  );
};
