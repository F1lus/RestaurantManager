import {Component, DestroyRef, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {Food} from "../../model/common";
import {ArrayPipe} from "../../pipes/array.pipe";
import {PricePipe} from "../../pipes/price.pipe";
import {TranslateModule} from "@ngx-translate/core";
import {NgIf} from "@angular/common";
import {takeUntilDestroyed} from "@angular/core/rxjs-interop";

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    RouterLink,
    ArrayPipe,
    PricePipe,
    TranslateModule,
    NgIf
  ],
  templateUrl: './menu.component.html',
})
export class MenuComponent implements OnInit {

  public menuItems: Food[] = [];
  public currentPage = 1;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly destroyRef: DestroyRef
  ) {
  }

  public ngOnInit() {
    this.route.data
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe(data => {
        this.menuItems = data['menu'].foods;
        this.currentPage = data['menu'].currentPage;
      })
  }

}
