import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-selectable-list',
  standalone: true,
  imports: [
    NgClass
  ],
  templateUrl: './selectable-list.component.html',
})
export class SelectableListComponent<T extends Record<any, any>> {

  @Input() public title?: string;

  @Input({required: true}) public listOfElements!: T[];

  @Output() public readonly selected = new EventEmitter<number>();

  protected selectedRow?: number;

  public onRowSelect(index: number) {
    this.selectedRow = index;
    this.selected.emit(index);
  }

}
