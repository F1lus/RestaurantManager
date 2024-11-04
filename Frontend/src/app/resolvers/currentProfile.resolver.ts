import {ResolveFn} from "@angular/router";
import {GeneralProfile} from "../model/auth";
import {inject} from "@angular/core";
import {ProfileService} from "../services/profile.service";

export const currentProfileResolver: ResolveFn<GeneralProfile | null> = () => {
  const profileService = inject(ProfileService);
  return profileService.getCurrentUser();
}
