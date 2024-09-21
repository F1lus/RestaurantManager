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
    {imgSrc: "assets/images/home/promo_pic_1.jpg", text: "home.promo_text.drinks"},
    {imgSrc: "assets/images/home/promo_pic_2.jpg", text: "home.promo_text.foods"},
    {imgSrc: "assets/images/home/promo_pic_3.jpg", text: "home.promo_text.service"}
  ] as const;

  public readonly reservationSteps = [
    {
      imgSrc: "assets/images/home/add_circle.png",
      title: "home.step_by_step.title.create_account",
      subtext: "home.step_by_step.subtext.create_account"
    },
    {
      imgSrc: "assets/images/home/create_reservation.png",
      title: "home.step_by_step.title.book_reservation",
      subtext: "home.step_by_step.subtext.book_reservation"
    },
    {
      imgSrc: "assets/images/home/review.png",
      title: "home.step_by_step.title.review",
      subtext: "home.step_by_step.subtext.review"
    },
    {
      imgSrc: "assets/images/home/done.png",
      title: "home.step_by_step.title.done",
      subtext: "home.step_by_step.subtext.done"
    },
  ] as const;

  protected readonly Date = Date;
}
