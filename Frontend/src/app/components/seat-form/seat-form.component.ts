import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {DashboardState, SeatForm, Seating} from "../../model/common";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {SeatingService} from "../../services/seating.service";
import {ComboBoxComponent} from "../combobox/combo-box.component";
import {TranslateModule} from "@ngx-translate/core";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {serverSideValidator} from "../../util/ServerSideValidation";
import {ErrorPipe} from "../../pipes/error.pipe";

@Component({
  selector: 'app-seat-form',
  standalone: true,
  imports: [
    ComboBoxComponent,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule,
    ErrorPipe
  ],
  templateUrl: './seat-form.component.html',
})
export class SeatFormComponent implements OnInit, OnChanges {

  @Input() public seating?: Seating;
  @Input() public isEditing = false;

  @Output() public readonly submit = new EventEmitter<void>();
  @Output() public readonly close = new EventEmitter<void>();

  public seatForm!: FormGroup<SeatForm>;
  public formError?: string;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly seatService: SeatingService,
    private readonly router: Router
  ) {
  }

  public ngOnInit(): void {
    this.seatForm = this.formBuilder.group({
      name: this.formBuilder.nonNullable.control('', [Validators.required]),
      personCount: this.formBuilder.nonNullable.control(0, [Validators.required, Validators.min(1)]),
    });

    this.updateForm();
  }

  public ngOnChanges(changes: SimpleChanges): void {
    if (!changes['seating'].isFirstChange()) {
      this.updateForm();
    }
  }

  private get request() {
    if (this.isEditing && !!this.seating) {
      return this.seatService.modifySeat({id: this.seating.id, ...this.seatForm.value} as Seating);
    }
    return this.seatService.createSeat(this.seatForm.value);
  }

  public handleSubmit() {
    this.seatForm.disable();
    this.request.subscribe({
      next: () => {
        this.seatForm.enable();
        if (this.isEditing) {
          this.submit.emit();
          this.close.emit();
          return;
        }

        void this.router.navigate([], {
          queryParams: {state: DashboardState.MODIFY_SEAT}, queryParamsHandling: 'merge'
        });
      },
      error: (err: HttpErrorResponse) => {
        this.seatForm.enable();
        if (typeof err.error === 'string') {
          this.formError = err.error;
          return;
        }
        const errors = err.error as Record<string, string>;
        serverSideValidator(this.seatForm, errors);
      }
    });
  }

  private updateForm() {
    this.seatForm.patchValue({
      name: this.seating?.name ?? '',
      personCount: this.seating?.personCount ?? 0,
    });
  }

  public handleCancel() {
    if (this.isEditing) {
      this.close.emit();
    }

    void this.router.navigate([], {
      queryParams: {state: DashboardState.MODIFY_SEAT}, queryParamsHandling: 'merge'
    })
  }
}
