import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {NavigationComponent} from "./components/navigation/navigation.component";
import {TranslationService} from "./services/translation.service";
import {NotificationComponent} from "./components/notification/notification.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavigationComponent, NotificationComponent],
  templateUrl: './app.component.html',
})
export class AppComponent implements OnInit {

  constructor(
    private readonly translationService: TranslationService,
  ) {
  }

  ngOnInit(): void {
    this.translationService.init();
  }

}
