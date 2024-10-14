import {ResolveFn} from "@angular/router";
import {inject} from "@angular/core";
import {ProfileService} from "../services/profile.service";
import {GeneralProfile} from "../model/auth";

export const profilesResolver: ResolveFn<GeneralProfile[]> = () => {
  const profileService = inject(ProfileService);
  return profileService.getAllUsers();
}
