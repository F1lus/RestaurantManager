import {ApplicationConfig, importProvidersFrom} from '@angular/core';
import {provideRouter, withViewTransitions} from '@angular/router';

import {routes} from './app.routes';
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {HttpClient, provideHttpClient, withInterceptors} from "@angular/common/http";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {apiInterceptor} from "./interceptors/api.interceptor";
import {authenticatedInterceptor} from "./interceptors/authenticated.interceptor";

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes, withViewTransitions()),
    provideHttpClient(
      withInterceptors([apiInterceptor, authenticatedInterceptor])
    ),
    importProvidersFrom(
      TranslateModule.forRoot({
        loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
        }
      })
    )
  ]
};
