import {Injectable} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

@Injectable({
  providedIn: 'root'
})
export class TranslationService {

  private readonly storageKey = 'lang';
  private readonly supportedLanguages = Object.freeze(['hu', 'en']);
  private currentLanguage: string = 'hu';

  constructor(
    private readonly translateService: TranslateService,
  ) {
  }

  public init() {
    const savedLang = localStorage.getItem(this.storageKey);
    if (savedLang) {
      this.language = savedLang;
      return;
    }

    this.language = navigator.language.split('-')[0].toLowerCase()
  }

  public get language() {
    return this.currentLanguage;
  }

  public set language(lang: string) {
    const nextLang = this.isLanguageSupported(lang) ? lang : 'en';

    this.currentLanguage = nextLang;
    this.changeLanguage(nextLang);
  }

  public get languages() {
    return [...this.supportedLanguages];
  }

  private changeLanguage(lang: string) {
    this.translateService.setDefaultLang(lang);
    this.translateService.use(lang);
    localStorage.setItem(this.storageKey, lang);
  }

  private isLanguageSupported(lang: string) {
    return this.supportedLanguages.includes(lang);
  }
}
