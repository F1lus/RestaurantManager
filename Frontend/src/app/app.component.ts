import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {TranslationService} from "./services/translation.service";
import {NavigationComponent} from "./components/navigation/navigation.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavigationComponent],
  templateUrl: './app.component.html',
})
export class AppComponent implements OnInit {

  constructor(
    private readonly translationService: TranslationService,
  ) {
  }

  public ngOnInit(): void {
    this.translationService.init();
  }

}
