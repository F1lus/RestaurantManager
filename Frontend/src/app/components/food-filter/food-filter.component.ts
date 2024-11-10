import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FoodFilter, FoodFilterForm} from "../../model/common";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {FoodService} from "../../services/food.service";
import {TranslateModule} from "@ngx-translate/core";
import {ComboBoxComponent} from "../combobox/combo-box.component";
import {map} from "rxjs";

@Component({
  selector: 'app-food-filter',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    TranslateModule,
    ComboBoxComponent
  ],
  templateUrl: './food-filter.component.html'
})
export class FoodFilterComponent implements OnInit {

  @Output() public readonly filter = new EventEmitter<FoodFilter>();

  public filterForm!: FormGroup<FoodFilterForm>;
  public allergens: string[] = [];

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly foodService: FoodService
  ) {
  }

  public ngOnInit(): void {
    this.filterForm = this.formBuilder.group({
      name: this.formBuilder.nonNullable.control(''),
      price: this.formBuilder.nonNullable.control(0, [Validators.min(0)]),
      allergens: this.formBuilder.nonNullable.control<string[]>([]),
    });

    this.foodService.getAllergens()
      .pipe(map(allergens => allergens.map(allergen => allergen.name)))
      .subscribe(allergens => this.allergens = allergens)
  }

  public handleSubmit(): void {
    this.filter.emit({
      name: this.filterForm.value.name?.trim().toLowerCase() ?? '',
      price: this.filterForm.value.price ?? 1,
      allergens: this.filterForm.value.allergens ?? [],
    })
  }

  public handleAllergensChange(allergens: string[]) {
    this.filterForm.patchValue({
      allergens: allergens.filter(allergen => allergen.trim().length > 0)
    });
  }
}
