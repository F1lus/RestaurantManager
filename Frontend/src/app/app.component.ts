import {Component, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {NavigationComponent} from "./components/navigation/navigation.component";
import {TranslationService} from "./services/translation.service";

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

  ngOnInit(): void {
    this.translationService.init();
  }

}
