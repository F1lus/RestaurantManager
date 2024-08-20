import {Injectable} from '@angular/core';
import {delayWhen, ReplaySubject, tap, timer} from "rxjs";
import {Notification} from "../model/common";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  public readonly delay = 10_000;
  private readonly _notification$ = new ReplaySubject<Notification>();

  public get notification$() {
    return this._notification$.pipe(
      delayWhen((_, index) => timer(this.delay)),
      tap(console.log)
    );
  }

  public addNotification(notification: Notification) {
    this._notification$.next(notification);
  }
}
