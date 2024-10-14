import {Component, OnInit} from '@angular/core';
import {GeneralProfile, ProfileType} from "../../model/auth";
import {ActivatedRoute} from "@angular/router";
import {take} from "rxjs";
import {SelectableListComponent} from "../selectable-list/selectable-list.component";
import {ProfileService} from "../../services/profile.service";

@Component({
  selector: 'app-manage-users',
  standalone: true,
  imports: [
    SelectableListComponent
  ],
  templateUrl: './manage-users.component.html',
  styleUrl: './manage-users.component.scss'
})
export class ManageUsersComponent implements OnInit {

  public profiles: GeneralProfile[] = [];

  public selectedProfile?: GeneralProfile
  protected readonly ProfileType = ProfileType;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly profileService: ProfileService,
  ) {
  }

  public ngOnInit() {
    this.route.data
      .pipe(take(1))
      .subscribe(data => {
        this.profiles = data['profiles'] as GeneralProfile[];
      });
  }

  public handleSelected(index: number) {
    this.selectedProfile = this.profiles[index];
  }

  public promoteUser() {
    this.profileService.modifyUserAuthority(this.selectedProfile!.id, ProfileType.WAITER)
      .subscribe({
        next: () => this.selectedProfile!.profileTypes.push(ProfileType.WAITER),
      })
  }

  public demoteUser() {
    this.profileService.modifyUserAuthority(this.selectedProfile!.id, ProfileType.USER)
      .subscribe({
        next: () => {
          const index = this.selectedProfile!.profileTypes.indexOf(ProfileType.WAITER)
          this.selectedProfile!.profileTypes.splice(index, 1)
        },
      })
  }
}
