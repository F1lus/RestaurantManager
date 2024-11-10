import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {SeatForm, Seating} from "../../model/common";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {TranslateModule} from "@ngx-translate/core";

@Component({
  selector: 'app-seat-filter',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    TranslateModule
  ],
  templateUrl: './seat-filter.component.html',
})
export class SeatFilterComponent implements OnInit {

  @Output() public readonly filter = new EventEmitter<Partial<Seating>>();

  public filterForm!: FormGroup<SeatForm>;

  constructor(
    private readonly formBuilder: FormBuilder,
  ) {
  }

  public ngOnInit(): void {
    this.filterForm = this.formBuilder.group({
      name: this.formBuilder.nonNullable.control(''),
      personCount: this.formBuilder.nonNullable.control(1, [Validators.min(1)]),
    });
  }

  public handleSubmit() {
    this.filter.emit({
      name: this.filterForm.value.name?.trim().toLowerCase() ?? '',
      personCount: this.filterForm.value.personCount ?? 1
    })
  }
}
