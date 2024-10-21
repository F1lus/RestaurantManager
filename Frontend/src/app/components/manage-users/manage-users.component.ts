import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {GeneralProfile, ProfileType} from "../../model/auth";
import {ActivatedRoute} from "@angular/router";
import {take} from "rxjs";
import {SelectableListComponent} from "../selectable-list/selectable-list.component";
import {ProfileService} from "../../services/profile.service";
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-manage-users',
  standalone: true,
  imports: [
    SelectableListComponent,
    NgOptimizedImage
  ],
  templateUrl: './manage-users.component.html',
})
export class ManageUsersComponent implements OnInit {

  @ViewChild("controls") controls?: TemplateRef<any>;

  public profiles: GeneralProfile[] = [];

  public readonly displayedColumns = ['fullName', 'email', 'phoneNumber'] as const;
  protected readonly ProfileType = ProfileType;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly profileService: ProfileService,
  ) {
  }

  public ngOnInit() {
    this.route.data
      .pipe(take(1))
      .subscribe(data => this.profiles = data['profiles'] as GeneralProfile[]);
  }

  public promoteUser(index: number) {
    this.profileService.modifyUserAuthority(this.profiles[index].id, ProfileType.WAITER)
      .subscribe({
        next: () => {
          this.profiles[index].profileTypes.push(ProfileType.WAITER)
        },
      })
  }

  public demoteUser(index: number) {
    this.profileService.modifyUserAuthority(this.profiles[index].id, ProfileType.USER)
      .subscribe({
        next: () => {
          const profileTypeIndex = this.profiles[index].profileTypes.indexOf(ProfileType.WAITER)
          this.profiles[index].profileTypes.splice(profileTypeIndex, 1)
        },
      })
  }
}
