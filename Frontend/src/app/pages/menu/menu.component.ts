import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {Subscription} from "rxjs";
import {Food} from "../../model/common";
import {ArrayPipe} from "../../pipes/array.pipe";
import {PricePipe} from "../../pipes/price.pipe";
import {TranslateModule} from "@ngx-translate/core";
import {NgIf} from "@angular/common";

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
export class MenuComponent implements OnInit, OnDestroy {

  public menuItems: Food[] = [];
  public currentPage = 1;
  private readonly subscription = new Subscription();

  constructor(private readonly route: ActivatedRoute) {
  }

  public ngOnInit() {
    this.subscription.add(
      this.route.data.subscribe(data => {
        this.menuItems = data['menu'].foods;
        this.currentPage = data['menu'].currentPage;
      })
    )
  }

  public ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
