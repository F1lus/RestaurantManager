import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {UserFilter, UserFilterForm} from "../../model/common";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {TranslateModule} from "@ngx-translate/core";

@Component({
  selector: 'app-user-filter',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    TranslateModule
  ],
  templateUrl: './user-filter.component.html'
})
export class UserFilterComponent implements OnInit {

  @Output() public readonly filter = new EventEmitter<UserFilter>();

  public filterForm!: FormGroup<UserFilterForm>;

  constructor(
    private readonly formBuilder: FormBuilder,
  ) {
  }

  public ngOnInit(): void {
    this.filterForm = this.formBuilder.group({
      fullName: this.formBuilder.nonNullable.control(''),
      email: this.formBuilder.nonNullable.control('', [Validators.email]),
      phoneNumber: this.formBuilder.nonNullable.control(''),
      waitersOnly: this.formBuilder.nonNullable.control(false)
    });
  }

  public handleSubmit() {
    this.filter.emit({
      fullName: this.filterForm.value.fullName?.trim().toLowerCase() ?? '',
      email: this.filterForm.value.email?.trim().toLowerCase() ?? '',
      phoneNumber: this.filterForm.value.phoneNumber?.trim() ?? '',
      waitersOnly: !!this.filterForm.value.waitersOnly,
    })
  }

}
