import {CanActivateFn, Router} from "@angular/router";
import {inject} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {of, switchMap} from "rxjs";
import {fromPromise} from "rxjs/internal/observable/innerFrom";
import {ProfileType} from "../model/auth";

export const hasPermission: CanActivateFn = (route) => {
  const permission = route.data["permission"] as ProfileType | null;
  if (!permission) {
    return true;
  }

  const authService = inject(AuthService);
  const router = inject(Router);

  return authService.getCurrentUser().pipe(
    switchMap(currentUser => {
      if (!currentUser) {
        return fromPromise(router.navigate(['/login']));
      }

      return !currentUser.profileTypes.includes(permission) ? fromPromise(router.navigate(['/overview'])) : of(true)
    })
  );
}
