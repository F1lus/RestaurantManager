import {Component, Input, OnInit} from '@angular/core';
import {GeneralProfile, ProfileForm} from "../../model/auth";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ProfileService} from "../../services/profile.service";
import {HttpErrorResponse} from "@angular/common/http";
import {serverSideValidator} from "../../util/ServerSideValidation";
import {ErrorPipe} from "../../pipes/error.pipe";
import {RouterLink} from "@angular/router";
import {TranslateModule} from "@ngx-translate/core";
import {NgClass} from "@angular/common";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-edit-profile-form',
  standalone: true,
  imports: [
    ErrorPipe,
    ReactiveFormsModule,
    RouterLink,
    TranslateModule,
    NgClass
  ],
  templateUrl: './edit-profile-form.component.html'
})
export class EditProfileFormComponent implements OnInit {

  @Input({required: true}) public profile!: GeneralProfile;

  public profileEditForm!: FormGroup<ProfileForm>;
  public formError?: string;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly profileService: ProfileService,
    private readonly authService: AuthService,
  ) {
  }

  public ngOnInit() {
    this.profileEditForm = this.formBuilder.group({
      email: this.formBuilder.nonNullable.control('', [Validators.required, Validators.email]),
      password: this.formBuilder.nonNullable.control('', [Validators.required, Validators.minLength(8)]),
      firstName: this.formBuilder.nonNullable.control('', [Validators.required]),
      lastName: this.formBuilder.nonNullable.control('', [Validators.required]),
      phoneNumber: this.formBuilder.nonNullable.control('', [Validators.required]),
      passwordRepeat: this.formBuilder.nonNullable.control('', [Validators.required, Validators.minLength(8)]),
    });

    this.updateForm();
  }

  public handleSubmit() {
    const email = this.profileEditForm.value.email ?? '';
    const password = this.profileEditForm.value.password ?? '';
    const firstName = this.profileEditForm.value.firstName ?? '';
    const lastName = this.profileEditForm.value.lastName ?? '';
    const phoneNumber = this.profileEditForm.value.phoneNumber ?? '';
    const passwordRepeat = this.profileEditForm.value.passwordRepeat ?? '';

    this.profileEditForm.disable();
    this.profileService.updateProfile(this.profile.id, {
      email,
      password,
      firstName,
      lastName,
      phoneNumber,
      passwordRepeat
    }).subscribe({
      next: response => {
        this.profileEditForm.enable()
        this.authService.authToken = response.token;
        this.profileEditForm.patchValue({
          password: '',
          passwordRepeat: '',
        })
        this.formError = undefined;
      },
      error: (err: HttpErrorResponse) => {
        this.profileEditForm.enable();
        if (typeof err.error === 'string') {
          this.formError = err.error;
          return;
        }
        const errors = err.error as Record<string, string>;
        serverSideValidator(this.profileEditForm, errors);
      }
    });
  }

  private updateForm() {
    const firstName = this.profile.fullName.substring(0, this.profile.fullName.indexOf(' '));
    const lastName = this.profile.fullName.substring(this.profile.fullName.indexOf(' ') + 1);
    this.profileEditForm.patchValue({
      firstName,
      lastName,
      email: this.profile.email,
      phoneNumber: this.profile.phoneNumber,
    });
  }

}
