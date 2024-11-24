import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EditProfileFormComponent} from './edit-profile-form.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {TranslateModule} from "@ngx-translate/core";
import {ErrorPipe} from "../../pipes/error.pipe";
import {ReactiveFormsModule} from "@angular/forms";
import {NgClass} from "@angular/common";
import {AuthService} from "../../services/auth.service";
import {ProfileService} from "../../services/profile.service";
import {ProfileType} from "../../model/auth";

fdescribe('EditProfileFormComponent', () => {
  let component: EditProfileFormComponent;
  let fixture: ComponentFixture<EditProfileFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        EditProfileFormComponent,
        HttpClientTestingModule,
        TranslateModule.forRoot(),
        ErrorPipe,
        ReactiveFormsModule,
        NgClass
      ],
      providers: [
        AuthService,
        ProfileService,
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(EditProfileFormComponent);
    component = fixture.componentInstance;

    component.profile = {
      id: "id",
      fullName: "John Doe",
      email: "john@email.com",
      phoneNumber: "123456789",
      profileTypes: [ProfileType.USER]
    }
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

});
