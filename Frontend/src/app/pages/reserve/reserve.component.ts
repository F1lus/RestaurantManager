import {Component} from '@angular/core';
import {ReserveFormComponent} from "../../components/reserve-form/reserve-form.component";

@Component({
  selector: 'app-reserve',
  standalone: true,
  imports: [
    ReserveFormComponent
  ],
  templateUrl: './reserve.component.html',
})
export class ReserveComponent {

}
