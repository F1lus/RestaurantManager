import {HttpEvent, HttpHandlerFn, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {inject} from "@angular/core";
import {AuthService} from "../services/auth.service";

export function apiInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
  const authService = inject(AuthService);
  if (!req.url.startsWith('/api')) {
    return next(req);
  }

  const modifiedReq = req.clone({
    url: environment.apiUrl + req.url,
    headers: req.headers.set(authService.authKey, authService.authToken)
  });
  return next(modifiedReq);
}
