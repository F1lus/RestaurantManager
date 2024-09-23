import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginParams, LoginResponse, RegisterParams} from "../model/auth";
import {tap} from "rxjs";

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
