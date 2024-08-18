import {Injectable} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

@Injectable({
  providedIn: 'root'
})
export class TranslationService {

  public defaultLanguage: string = 'hu';

  private readonly supportedLanguages = ['en', 'hu'];

  constructor(
    private readonly translateService: TranslateService,
  ) {
  }

  public init() {
    const savedLang = localStorage.getItem('lang');
    if (savedLang) {
      this.defaultLanguage = savedLang;
    }

    this.changeLanguage(this.defaultLanguage);
  }

  public changeLanguage(lang: string) {
    if (this.isLanguageSupported(lang)) {
      this.translateService.setDefaultLang(lang);
      this.translateService.use(lang);
      localStorage.setItem('lang', lang);
    }
  }

  private isLanguageSupported(lang: string) {
    return this.supportedLanguages.includes(lang);
  }
}
