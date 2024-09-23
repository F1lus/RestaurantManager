import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {LoginForm} from "../../../model/auth";
import {TranslateModule} from "@ngx-translate/core";
import {Router} from "@angular/router";
import {serverSideValidator} from "../../../util/ServerSideValidation";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, TranslateModule],
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  public loginForm!: FormGroup<LoginForm>

  constructor(
    private readonly authService: AuthService,
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
    this.authService.login({email, password}).subscribe({
      next: () => void this.router.navigate(['/home']),
      error: err => {
        const errors = err.error as string[];
        serverSideValidator(this.loginForm, errors);
      },
    })
  }
}
