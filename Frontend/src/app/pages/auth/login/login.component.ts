import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {LoginForm} from "../../../model/auth";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  public loginForm!: FormGroup<LoginForm>

  constructor(
    private readonly authService: AuthService,
    private readonly formBuilder: FormBuilder
  ) {
  }

  public ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: this.formBuilder.nonNullable.control('', [Validators.required, Validators.email]),
      password: this.formBuilder.nonNullable.control('', [Validators.required, Validators.min(8)])
    });
  }
}
