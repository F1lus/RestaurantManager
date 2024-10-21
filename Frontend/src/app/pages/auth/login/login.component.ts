import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {LoginForm} from "../../../model/auth";
import {TranslateModule} from "@ngx-translate/core";
import {Router} from "@angular/router";
import {serverSideValidator} from "../../../util/ServerSideValidation";
import {ErrorPipe} from "../../../pipes/error.pipe";
import {NgClass} from "@angular/common";
import {HttpErrorResponse} from "@angular/common/http";
import {LoggedInService} from "../../../services/logged-in.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, TranslateModule, ErrorPipe, NgClass],
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  public loginForm!: FormGroup<LoginForm>
  public formError?: string;

  constructor(
    private readonly authService: AuthService,
    private readonly loggedInService: LoggedInService,
    private readonly formBuilder: FormBuilder,
    private router: Router,
  ) {
  }

  public ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: this.formBuilder.nonNullable.control('', [Validators.required, Validators.email]),
      password: this.formBuilder.nonNullable.control('', [Validators.required, Validators.min(8)])
    });
  }

  public handleSubmit() {
    const email = this.loginForm.value.email ?? '';
    const password = this.loginForm.value.password ?? '';
    this.loginForm.disable();
    this.authService.login({email, password}).subscribe({
      next: () => {
        this.loggedInService.attemptLogin();
        void this.router.navigate(['/overview']);
      },
      error: (err: HttpErrorResponse) => {
        this.loginForm.enable();
        if (typeof err.error === 'string') {
          this.formError = err.error;
          return;
        }
        const errors = err.error as Record<string, string>;
        serverSideValidator(this.loginForm, errors);
      },
    })
  }

}
