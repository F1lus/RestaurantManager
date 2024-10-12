import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GeneralProfile} from "../model/auth";
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
}
