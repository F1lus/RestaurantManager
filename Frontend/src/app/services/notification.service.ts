import {Injectable} from '@angular/core';
import {delayWhen, ReplaySubject, tap, timer} from "rxjs";
import {Notification} from "../model/common";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  public readonly delay = 10_000;
  private readonly _notification$ = new ReplaySubject<Notification>();
  private queueLength = 0;

  public readonly notification$ = this._notification$.pipe(
    delayWhen(() => {
      const delay = timer(this.delay * this.queueLength)
      this.queueLength++;
      return delay;
    }),
    tap(() => this.queueLength--),
  );

  public addNotification(notification: Notification) {
    this._notification$.next(notification);
  }
}
