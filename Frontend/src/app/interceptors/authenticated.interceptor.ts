import {HttpErrorResponse, HttpEvent, HttpHandlerFn, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {inject} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {LoggedInService} from "../services/logged-in.service";

export function authenticatedInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const authService = inject(AuthService);
  const router = inject(Router);
  const loggedInService = inject(LoggedInService);

  return next(req).pipe(
    catchError((err: HttpErrorResponse) => {
      console.error('An error has been caught:', err);

      if (err.status === 401 || err.status === 403) {
        authService.removeAuthToken();
        loggedInService.logout();
        void router.navigate(['/login']);
      }

      return throwError(() => err);
    })
  );
}
