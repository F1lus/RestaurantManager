import {Injectable} from '@angular/core';
import {delayWhen, ReplaySubject, tap, timer} from "rxjs";
import {Notification} from "../model/common";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  public readonly delay = 10_000;
  private readonly _notification$ = new ReplaySubject<Notification | null>();
  private queueLength = 0;

  public readonly notification$ = this._notification$.pipe(
    delayWhen(() => {
      const delay = timer(this.delay * this.queueLength)
      this.queueLength++;
      return delay;
    }),
    tap(() => {
      this.queueLength--;
      console.log(this.queueLength);
      if (this.queueLength <= 0) {
        this.addNotification(null);
      }
    }),
  );

  public addNotification(notification: Notification | null) {
    this._notification$.next(notification);
  }
}
