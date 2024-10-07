import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {TranslationService} from "./services/translation.service";
import {NavigationComponent} from "./components/navigation/navigation.component";
import {LoggedInService} from "./services/logged-in.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavigationComponent],
  templateUrl: './app.component.html',
})
export class AppComponent implements OnInit {

  constructor(
    private readonly translationService: TranslationService,
    private readonly loggedInService: LoggedInService,
  ) {
  }

  public ngOnInit(): void {
    this.translationService.init();
    this.loggedInService.attemptLogin();
  }

}
