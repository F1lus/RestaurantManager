import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GeneralProfile, LoginResponse, ProfileRequest, ProfileType} from "../model/auth";
import {catchError, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private readonly baseUrl = '/api/profile';

  constructor(private readonly http: HttpClient) {
  }

  public getCurrentUser() {
    return this.http.get<GeneralProfile | null>(this.baseUrl)
      .pipe(catchError(() => of(null)));
  }

  public getAllUsers() {
    return this.http.get<GeneralProfile[]>(`${this.baseUrl}/all`);
  }

  public modifyUserAuthority(id: string, profileType: ProfileType) {
    return this.http.put<void>(`${this.baseUrl}/${id}`, {profileType});
  }

  public updateProfile(id: string, request: ProfileRequest) {
    const {
      email,
      firstName,
      lastName,
      phoneNumber,
      password,
      passwordRepeat
    } = request;
    return this.http.put<LoginResponse>(`${this.baseUrl}/edit/${id}`, {
      email,
      fullName: `${firstName} ${lastName}`,
      phoneNumber,
      password,
      passwordRepeat
    })
  }
}
