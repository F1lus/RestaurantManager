import {
  AfterViewInit,
  Component,
  DestroyRef,
  ElementRef,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
  ViewChild
} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {NgClass} from "@angular/common";
import {takeUntilDestroyed} from "@angular/core/rxjs-interop";
import {TranslateModule} from "@ngx-translate/core";

@Component({
  selector: 'app-combobox',
  standalone: true,
  imports: [
    NgClass,
    ReactiveFormsModule,
    TranslateModule
  ],
  templateUrl: './combo-box.component.html',
})
export class ComboBoxComponent implements OnInit, OnChanges, AfterViewInit {

  @Input({required: true}) public autoCompletes: string[] = [];
  @Input() public initialValue: string[] = [];

  @Output() public changes = new EventEmitter<string[]>();

  @ViewChild("input", {static: true}) input!: ElementRef<HTMLInputElement>;

  public filteredValues: string[] = [];
  public form!: FormGroup<{ elements: FormControl<string[]> }>;

  public hasValue = false;

  private isChangeInternal = false;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly destroyRef: DestroyRef
  ) {
  }

  public ngOnInit(): void {
    this.form = this.formBuilder.group({
      elements: this.formBuilder.nonNullable.control<string[]>([]),
    });

    this.form.valueChanges
      .pipe(takeUntilDestroyed(this.destroyRef))
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

          return !elements.map(element => element.toLowerCase()).includes(ac.toLowerCase())
            && ac.toLowerCase().includes(value.toLowerCase());
        })

        this.hasValue = value.length > 0 && this.filteredValues.length > 0

        this.isChangeInternal = true;
        this.changes.emit(elements);
      })
  }

  public ngOnChanges(changes: SimpleChanges) {
    if (!changes['initialValue'].isFirstChange() && !this.isChangeInternal) {
      this.input.nativeElement.value = this.initialValue.join(", ");
    }
    this.isChangeInternal = false;
  }

  public handleChange() {
    const element = this.input.nativeElement;
    const array = element.value.split(",").map(value => value.trim());
    this.form.patchValue({
      elements: array
    })
  }

  public handleElementClick(value: string) {
    if (!this.form.value.elements) {
      return;
    }
    this.form.value.elements[this.form.value.elements.length - 1] = value

    this.input.nativeElement.value = this.form.value.elements.join(", ");
    this.input.nativeElement.dispatchEvent(new Event('input'));
  }

  public ngAfterViewInit(): void {
    this.input.nativeElement.value = this.initialValue.join(", ");
  }

}
