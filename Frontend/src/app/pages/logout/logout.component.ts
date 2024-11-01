import {Component, OnInit} from '@angular/core';
import {LoggedInService} from "../../services/logged-in.service";

@Component({
  selector: 'app-logout',
  standalone: true,
  imports: [],
  templateUrl: './logout.component.html',
})
export class LogoutComponent implements OnInit {

  constructor(private readonly loggedInService: LoggedInService) {
  }

  public ngOnInit() {
    this.loggedInService.logout();
  }
}
