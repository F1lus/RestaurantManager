import {Component, OnInit} from '@angular/core';
import {Food} from "../../model/common";
import {ActivatedRoute} from "@angular/router";
import {map, take} from "rxjs";
import {SelectableListComponent} from "../selectable-list/selectable-list.component";

@Component({
  selector: 'app-manage-menu',
  standalone: true,
  imports: [
    SelectableListComponent
  ],
  templateUrl: './manage-menu.component.html',
})
export class ManageMenuComponent implements OnInit {

  public menu: Food[] = [];

  constructor(private readonly route: ActivatedRoute) {
  }

  public ngOnInit(): void {
    this.route.data
      .pipe(take(1), map(data => data['menu']['foods'] as Food[]))
      .subscribe(data => this.menu = data);
  }

}
