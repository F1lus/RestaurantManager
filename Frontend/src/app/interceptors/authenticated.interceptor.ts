import {HttpErrorResponse, HttpEvent, HttpHandlerFn, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {inject} from "@angular/core";
import {AuthService} from "../services/auth.service";
import {LoggedInService} from "../services/logged-in.service";
import {Router} from "@angular/router";

const watchedStatuses = Object.freeze([401, 403])

export function authenticatedInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const authService = inject(AuthService);
  const router = inject(Router);
  const loggedInService = inject(LoggedInService);

  return next(req).pipe(
    catchError((err: HttpErrorResponse) => {
      if (watchedStatuses.includes(err.status)) {
        authService.removeAuthToken();
        loggedInService.logout();
        void router.navigate(['/login']);
      }

      return throwError(() => err);
    })
  );
}
