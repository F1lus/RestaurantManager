import {Component} from '@angular/core';
import {NotificationService} from "../../services/notification.service";
import {AsyncPipe, NgIf} from "@angular/common";
import {Severity} from "../../model/common";

@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [
    NgIf,
    AsyncPipe
  ],
  templateUrl: './notification.component.html',
})
export class NotificationComponent {

  constructor(
    public readonly notificationService: NotificationService
  ) {
    console.log('aaa')
    this.notificationService.addNotification({
      title: 'aaa',
      text: 'bbb',
      severity: Severity.INFO
    })
  }
}
