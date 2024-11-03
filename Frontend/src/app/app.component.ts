import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {TranslationService} from "./services/translation.service";
import {NavigationComponent} from "./components/navigation/navigation.component";
import {LoggedInService} from "./services/logged-in.service";
import {animate, query, stagger, style, transition} from "@angular/animations";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavigationComponent],
  templateUrl: './app.component.html',
  animations: [
    transition(':enter', [
      query('.hero', [
        style({opacity: 0, transform: 'translateY(-100px)'}),
        stagger(30, [
          animate('500ms cubic-bezier(0.35, 0, 0.25, 1)', style({opacity: 1, transform: 'none'})),
        ]),
      ]),
    ]),
  ]
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
