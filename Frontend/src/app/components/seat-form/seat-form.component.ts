import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SeatForm, Seating} from "../../model/common";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {SeatingService} from "../../services/seating.service";
import {ComboBoxComponent} from "../combobox/combo-box.component";
import {TranslateModule} from "@ngx-translate/core";

@Component({
  selector: 'app-seat-form',
  standalone: true,
  imports: [
    ComboBoxComponent,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule
  ],
  templateUrl: './seat-form.component.html',
})
export class SeatFormComponent implements OnInit {

  @Input() public seating?: Seating;
  @Input() public isEditing = false;

  @Output() public readonly submit = new EventEmitter<void>();
  @Output() public readonly close = new EventEmitter<void>();

  public seatForm!: FormGroup<SeatForm>;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly seatService: SeatingService,
  ) {
  }

  public ngOnInit(): void {
    this.seatForm = this.formBuilder.group({
      name: this.formBuilder.nonNullable.control('', [Validators.required]),
      personCount: this.formBuilder.nonNullable.control(0, [Validators.required, Validators.min(1)]),
    });

    this.updateForm();
  }

  private updateForm() {
    this.seatForm.patchValue({
      name: this.seating?.name ?? '',
      personCount: this.seating?.personCount ?? 0,
    });
  }
}
