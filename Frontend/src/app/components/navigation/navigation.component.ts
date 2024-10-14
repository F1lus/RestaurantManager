import {Component, OnInit} from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {RouterLink, RouterLinkActive} from "@angular/router";
import {NavLink} from "../../model/navigation";
import {AsyncPipe, NgClass, NgOptimizedImage, UpperCasePipe} from "@angular/common";
import {NavigationService} from "../../services/navigation.service";
import {Observable} from "rxjs";
import {TranslationService} from "../../services/translation.service";

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [
    TranslateModule,
    RouterLink,
    NgOptimizedImage,
    RouterLinkActive,
    AsyncPipe,
    UpperCasePipe,
    NgClass
  ],
  templateUrl: './navigation.component.html',
})
export class NavigationComponent implements OnInit {

  public navLinks!: Observable<NavLink[]>;
  public supportedLanguages!: string[];
  public currentLanguage!: string;

  public isMobileViewHidden = true;

  constructor(
    private readonly navigationService: NavigationService,
    private readonly translationService: TranslationService,
  ) {
  }

  public ngOnInit(): void {
    this.navLinks = this.navigationService.navLinksListener;
    this.supportedLanguages = this.translationService.languages;
    this.currentLanguage = this.translationService.language;
  }

  public changeLanguage(lang: string) {
    this.translationService.language = lang;
    this.currentLanguage = this.translationService.language;
  }

  public clickOnHamburger(): void {
    this.isMobileViewHidden = !this.isMobileViewHidden;
  }
}
