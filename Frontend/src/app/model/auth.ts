import {FormControl} from "@angular/forms";

export enum ProtectionLevel {
  NONE,
  UNAUTHORIZED,
  AUTHORIZED
}

export enum ProfileType {
  USER = "USER",
  WAITER = "WAITER",
  ADMIN = "ADMIN",
}

export interface GeneralProfile {
  id: string;
  fullName: string;
  email: string;
  phoneNumber: string;
  profileTypes: ProfileType[];
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface LoginForm {
  email: FormControl<string>;
  password: FormControl<string>;
}

export interface ProfileRequest extends LoginRequest {
  firstName: string;
  lastName: string;
  phoneNumber: string;
  passwordRepeat: string;
}

export interface ProfileForm extends LoginForm {
  firstName: FormControl<string>;
  lastName: FormControl<string>;
  phoneNumber: FormControl<string>;
  passwordRepeat: FormControl<string>;
}

export interface LoginResponse {
  token: string;
}
