import {Component, DestroyRef, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {GeneralProfile, ProfileType} from "../../model/auth";
import {ActivatedRoute} from "@angular/router";
import {BehaviorSubject, map, switchMap, take} from "rxjs";
import {SelectableListComponent} from "../selectable-list/selectable-list.component";
import {ProfileService} from "../../services/profile.service";
import {NgOptimizedImage} from "@angular/common";
import {UserFilterComponent} from "../user-filter/user-filter.component";
import {UserFilter} from "../../model/common";
import {takeUntilDestroyed} from "@angular/core/rxjs-interop";

@Component({
  selector: 'app-manage-users',
  standalone: true,
  imports: [
    SelectableListComponent,
    NgOptimizedImage,
    UserFilterComponent
  ],
  templateUrl: './manage-users.component.html',
})
export class ManageUsersComponent implements OnInit {

  @ViewChild("controls") controls?: TemplateRef<any>;

  public displayedProfiles: GeneralProfile[] = []
  public readonly displayedColumns = ['fullName', 'email', 'phoneNumber'] as const;

  public readonly filterSubject = new BehaviorSubject<UserFilter | null>(null);
  protected readonly ProfileType = ProfileType;

  private profiles: GeneralProfile[] = [];

  constructor(
    private readonly route: ActivatedRoute,
    private readonly profileService: ProfileService,
    private readonly destroyRef: DestroyRef
  ) {
  }

  public ngOnInit() {
    this.route.data
      .pipe(take(1), map(data => data['profiles'] as GeneralProfile[]))
      .subscribe(data => {
        this.profiles = data;
        this.displayedProfiles = data;
      });

    this.filterSubject.pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((filter) => {
        if (!filter) {
          return;
        }

        this.displayedProfiles = this.profiles
          .filter(profile => profile.fullName.toLowerCase().includes(filter.fullName))
          .filter(profile => profile.email.toLowerCase().includes(filter.email))
          .filter(profile => profile.phoneNumber.includes(filter.phoneNumber))
          .filter(profile => filter.waitersOnly ? profile.profileTypes.includes(ProfileType.WAITER) : true);
      })
  }

  public promoteUser(index: number) {
    this.profileService.modifyUserAuthority(this.displayedProfiles[index].id, ProfileType.WAITER)
      .pipe(
        switchMap(() => this.profileService.getAllUsers())
      )
      .subscribe({
        next: data => {
          this.profiles = data;
          this.displayedProfiles = data;
          this.filterSubject.next(this.filterSubject.value);
        },
      })
  }

  public demoteUser(index: number) {
    this.profileService.modifyUserAuthority(this.displayedProfiles[index].id, ProfileType.USER)
      .pipe(
        switchMap(() => this.profileService.getAllUsers())
      )
      .subscribe({
        next: data => {
          this.profiles = data;
          this.displayedProfiles = data;
          this.filterSubject.next(this.filterSubject.value);
        },
      })
  }

  public handleFilter(filter: UserFilter) {
    this.filterSubject.next(filter);
  }
}
