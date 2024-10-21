import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges, TemplateRef} from '@angular/core';
import {NgClass, NgTemplateOutlet} from "@angular/common";
import {ArrayPipe} from "../../pipes/array.pipe";

@Component({
  selector: 'app-selectable-list',
  standalone: true,
  imports: [
    NgClass,
    ArrayPipe,
    NgTemplateOutlet
  ],
  templateUrl: './selectable-list.component.html',
})
export class SelectableListComponent<T extends Record<any, any>> implements OnChanges {

  @Input() public title?: string;
  @Input() public controlsTemplate?: TemplateRef<any>;
  @Input() public isSelectionEnabled = false;

  @Input({required: true}) public displayColumns!: readonly string[];
  @Input({required: true}) public listOfElements!: T[];

  @Output() public readonly selected = new EventEmitter<number>();

  protected selectedRow?: number;

  public ngOnChanges(changes: SimpleChanges) {
    console.log(changes);
  }

  public onRowSelect(index: number) {
    if (!this.isSelectionEnabled) {
      return;
    }

    this.selectedRow = index;
    this.selected.emit(index);
  }

}
