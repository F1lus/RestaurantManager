import {Component} from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {DatePipe, NgClass, NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    TranslateModule,
    NgOptimizedImage,
    DatePipe,
    NgClass
  ],
  templateUrl: './home.component.html',
})
export class HomeComponent {

  public readonly promoCards = [
    {id: 1, imgSrc: "assets/images/home/promo_pic_1.jpg", text: "home.promo_text.drinks"},
    {id: 2, imgSrc: "assets/images/home/promo_pic_2.jpg", text: "home.promo_text.foods"},
    {id: 3, imgSrc: "assets/images/home/promo_pic_3.jpg", text: "home.promo_text.service"}
  ] as const;

  public readonly reservationSteps = [
    {
      id: 1,
      imgSrc: "assets/images/home/add_circle.png",
      title: "home.step_by_step.title.create_account",
      subtext: "home.step_by_step.subtext.create_account"
    },
    {
      id: 2,
      imgSrc: "assets/images/home/create_reservation.png",
      title: "home.step_by_step.title.book_reservation",
      subtext: "home.step_by_step.subtext.book_reservation"
    },
    {
      id: 3,
      imgSrc: "assets/images/home/review.png",
      title: "home.step_by_step.title.review",
      subtext: "home.step_by_step.subtext.review"
    },
    {
      id: 4,
      imgSrc: "assets/images/home/done.png",
      title: "home.step_by_step.title.done",
      subtext: "home.step_by_step.subtext.done"
    },
  ] as const;

  public readonly currentDate = Date.now();
}
