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

export interface LoginForm {
  email: FormControl<string>;
  password: FormControl<string>;
}

export interface RegisterForm extends LoginForm {
  firstName: FormControl<string>;
  lastName: FormControl<string>;
  phoneNumber: FormControl<string>;
  passwordRepeat: FormControl<string>;
}
