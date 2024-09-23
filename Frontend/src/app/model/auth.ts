import {FormControl} from "@angular/forms";

export enum ProtectionLevel {
  NONE,
  UNAUTHORIZED,
  AUTHORIZED
}

export enum AccountType {
  USER,
  WAITER,
  ADMIN
}

export interface LoginParams {
  email: string;
  password: string;
}

export interface LoginForm {
  email: FormControl<string>;
  password: FormControl<string>;
}

export interface RegisterParams extends LoginParams {
  firstName: string;
  lastName: string;
  phoneNumber: string;
  passwordRepeat: string;
}

export interface RegisterForm extends LoginForm {
  firstName: FormControl<string>;
  lastName: FormControl<string>;
  phoneNumber: FormControl<string>;
  passwordRepeat: FormControl<string>;
}

export interface LoginResponse {
  token: string;
}
