import {Injectable, OnDestroy} from '@angular/core';
import {BehaviorSubject, filter, Subscription, switchMap, tap, timer} from "rxjs";
import {GeneralProfile} from "../model/auth";
import {ProfileService} from "./profile.service";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class LoggedInService implements OnDestroy {

  /**
   * 1 minute
   */
  private readonly TIMER_INTERVAL = 60_000
  private isInitialized = false;
  private shouldEmitValue = false;

  private readonly _userInfo = new BehaviorSubject<GeneralProfile | null>(null);
  private readonly subscription = new Subscription();
  private readonly timedQuery = timer(0, this.TIMER_INTERVAL)
    .pipe(
      filter(() => this.shouldEmitValue),
      switchMap(() => this.profileService.getCurrentUser()),
      tap(userInfo => this._userInfo.next(userInfo))
    );

  constructor(
    private readonly profileService: ProfileService,
    private readonly authService: AuthService,
  ) {
  }

  public get userInfo() {
    return this._userInfo.asObservable();
  }

  public attemptLogin() {
    this.shouldEmitValue = true;
    if (!this.isInitialized && !!this.authService.authToken) {
      this.subscription.add(this.timedQuery.subscribe());
      this.isInitialized = true;
    }
  }

  public logout() {
    this.shouldEmitValue = false;
    this._userInfo.next(null);
  }

  public ngOnDestroy() {
    this.subscription?.unsubscribe();
  }
}
