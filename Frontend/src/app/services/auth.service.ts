import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginParams, LoginResponse, RegisterParams} from "../model/auth";
import {map, Observable, tap} from "rxjs";
import {ProfileService} from "./profile.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public readonly authKey = 'Authorization';

  private readonly baseUrl = '/api/auth';

  constructor(
    private readonly http: HttpClient,
    private readonly profileService: ProfileService,
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
    return this.profileService.getCurrentUser()
      .pipe(
        map(response => !!response)
      )
  }

  public login(params: LoginParams) {
    const {email, password} = params;
    return this.http.post<LoginResponse>(`${this.baseUrl}/login`, {email, password})
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

    return this.http.post(`${this.baseUrl}/register`, {
      email,
      fullName: `${firstName} ${lastName}`,
      phoneNumber,
      password,
      passwordRepeat
    })
  }
}
