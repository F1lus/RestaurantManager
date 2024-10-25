import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {Subscription} from "rxjs";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-combobox',
  standalone: true,
  imports: [
    NgClass
  ],
  templateUrl: './combo-box.component.html',
  styleUrl: './combo-box.component.scss'
})
export class ComboBoxComponent implements OnInit, OnChanges, OnDestroy {

  @Input() public autoCompletes: string[] = [];

  @Output() public changes = new EventEmitter<string[]>();

  public filteredValues: string[] = [];
  public form!: FormGroup<{ elements: FormControl<string[]> }>;

  public hasValue = false;
  public isFocused = false;

  private sub = new Subscription();

  constructor(private readonly formBuilder: FormBuilder) {
  }

  public ngOnInit(): void {
    this.form = this.formBuilder.group({
      elements: this.formBuilder.nonNullable.control<string[]>([]),
    });

    this.sub.add(
      this.form.valueChanges
        .subscribe(() => {
          const {elements} = this.form.value;
          if (!elements || elements.length === 0) {
            return;
          }
          const value = elements[elements.length - 1];

          this.filteredValues = this.autoCompletes.filter(ac => {
            if (ac.toLowerCase() === value.toLowerCase()) {
              return false;
            }

            return !elements.includes(ac.toLowerCase()) && ac.toLowerCase().includes(value.toLowerCase());
          })

          this.hasValue = value.length > 0 && this.filteredValues.length > 0

          this.changes.emit(elements);
        })
    );
  }

  public ngOnChanges(changes: SimpleChanges) {
    if (changes['autoCompletes']) {
      this.filteredValues = this.autoCompletes;
    }
  }

  public handleChange(target: EventTarget | null) {
    if (!target) {
      return;
    }

    const element = target as HTMLInputElement;
    const array = element.value.split(",").map(value => value.trim());
    this.form.patchValue({
      elements: array
    })
  }

  public handleElementClick(value: string, input: HTMLInputElement) {
    if (!this.form.value.elements) {
      return;
    }
    const element = this.form.value.elements[this.form.value.elements.length - 1].toLowerCase();

    const index = value.toLowerCase().lastIndexOf(element);
    const substring = value.substring(index + element.length);

    input.value = `${input.value}${substring}`;
    input.dispatchEvent(new Event('input'));
  }

  public ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
