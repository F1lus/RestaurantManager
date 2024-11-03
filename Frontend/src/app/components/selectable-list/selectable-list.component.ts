import {Component, Input, TemplateRef} from '@angular/core';
import {NgClass, NgTemplateOutlet} from "@angular/common";
import {ArrayPipe} from "../../pipes/array.pipe";
import {TranslateModule} from "@ngx-translate/core";

@Component({
  selector: 'app-selectable-list',
  standalone: true,
  imports: [
    NgClass,
    ArrayPipe,
    NgTemplateOutlet,
    TranslateModule
  ],
  templateUrl: './selectable-list.component.html',
})
export class SelectableListComponent<T extends Record<any, any>> {

  @Input() public title?: string;
  @Input() public controlsTemplate?: TemplateRef<any>;
  @Input() public isSelectionEnabled = false;

  @Input({required: true}) public displayColumns!: readonly string[];
  @Input({required: true}) public listOfElements!: T[];

}
