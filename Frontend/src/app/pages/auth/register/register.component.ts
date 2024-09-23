import {Component, OnInit} from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {AuthService} from "../../../services/auth.service";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {RegisterForm} from "../../../model/auth";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    TranslateModule,
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
})
export class RegisterComponent implements OnInit {

  public registerForm!: FormGroup<RegisterForm>;

  constructor(
    private readonly authService: AuthService,
    private readonly formBuilder: FormBuilder
  ) {
  }

  public ngOnInit() {
    this.registerForm = this.formBuilder.group({
      email: this.formBuilder.nonNullable.control('', [Validators.required, Validators.email]),
      password: this.formBuilder.nonNullable.control('', [Validators.required, Validators.min(8)]),
      firstName: this.formBuilder.nonNullable.control('', [Validators.required]),
      lastName: this.formBuilder.nonNullable.control('', [Validators.required]),
      phoneNumber: this.formBuilder.nonNullable.control('', [Validators.required]),
      passwordRepeat: this.formBuilder.nonNullable.control('', [Validators.required, Validators.min(8)]),
    });
  }

}
