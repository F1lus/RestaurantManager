import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

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

  public login() {
    return this.http.post('/api/auth/login', {email: null, password: null})
  }
}
