import {Component, OnInit} from '@angular/core';
import {TranslateModule} from "@ngx-translate/core";
import {AuthService} from "../../../services/auth.service";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {RegisterForm} from "../../../model/auth";
import {Router} from "@angular/router";
import {serverSideValidator} from "../../../util/ServerSideValidation";

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
    private readonly formBuilder: FormBuilder,
    private readonly router: Router,
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

  public handleSubmit() {
    const email = this.registerForm.value.email ?? '';
    const password = this.registerForm.value.password ?? '';
    const firstName = this.registerForm.value.firstName ?? '';
    const lastName = this.registerForm.value.lastName ?? '';
    const phoneNumber = this.registerForm.value.phoneNumber ?? '';
    const passwordRepeat = this.registerForm.value.passwordRepeat ?? '';

    this.authService.register({
      email,
      password,
      firstName,
      lastName,
      phoneNumber,
      passwordRepeat
    }).subscribe({
      next: () => void this.router.navigate(['auth', 'login']),
      error: err => {
        const errors = err.error as string[];
        serverSideValidator(this.registerForm, errors);
      }
    });
  }

}
