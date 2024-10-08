import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GeneralProfile, LoginParams, LoginResponse, RegisterParams} from "../model/auth";
import {catchError, map, Observable, of, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public readonly authKey = 'Authorization';

  constructor(
    private readonly http: HttpClient
  ) {
  }

  public get authToken() {
    const token = localStorage.getItem(this.authKey)
    if (!token) {
      return ''
    }
    return `Bearer ${token}`;
  }

  public set authToken(token: string) {
    localStorage.setItem(this.authKey, token);
  }

  public removeAuthToken() {
    localStorage.removeItem(this.authKey);
  }

  public isLoggedIn(): Observable<boolean> {
    return this.getCurrentUser()
      .pipe(
        map(response => !!response)
      )
  }

  public getCurrentUser() {
    return this.http.get<GeneralProfile | null>('/api/auth').pipe(
      catchError(() => of(null)),
    )
  }

  public login(params: LoginParams) {
    const {email, password} = params;
    return this.http.post<LoginResponse>('/api/auth/login', {email, password})
      .pipe(
        tap(response => this.authToken = response.token)
      )
  }

  public register(params: RegisterParams) {
    const {
      email,
      firstName,
      lastName,
      phoneNumber,
      password,
      passwordRepeat
    } = params;

    return this.http.post('/api/auth/register', {
      email,
      fullName: `${firstName} ${lastName}`,
      phoneNumber,
      password,
      passwordRepeat
    })
  }
}
