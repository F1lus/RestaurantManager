import {animate, style, transition} from "@angular/animations";

export const routeAnimation = transition(':enter', [
  style({opacity: 0, transform: 'translateY(-100px)'}),
  animate('500ms cubic-bezier(0.35, 0, 0.25, 1)', style({opacity: 1, transform: 'none'})),
]);
