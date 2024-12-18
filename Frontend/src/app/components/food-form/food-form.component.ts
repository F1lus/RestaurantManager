import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {CreateFoodRequest, DashboardState, Food, FoodForm} from "../../model/common";
import {ErrorPipe} from "../../pipes/error.pipe";
import {TranslateModule} from "@ngx-translate/core";
import {environment} from "../../../environments/environment";
import {ComboBoxComponent} from "../combobox/combo-box.component";
import {Router} from "@angular/router";
import {map} from "rxjs";
import {FoodService} from "../../services/food.service";
import {serverSideValidator} from "../../util/ServerSideValidation";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-food-form',
  standalone: true,
  imports: [
    ErrorPipe,
    ReactiveFormsModule,
    TranslateModule,
    ComboBoxComponent
  ],
  templateUrl: './food-form.component.html',
})
export class FoodFormComponent implements OnInit, OnChanges {

  @Input() public food: Food | null = null;
  @Input() public isEditing = false;

  @Output() public readonly submit = new EventEmitter<void>();
  @Output() public readonly close = new EventEmitter<void>();

  public foodForm!: FormGroup<FoodForm>;
  public allergens: string[] = [];
  public formError?: string;

  public currency = environment.priceTag;

  constructor(
    private readonly formBuilder: FormBuilder,
    private readonly foodService: FoodService,
    private readonly router: Router,
  ) {
  }

  public ngOnInit(): void {
    this.foodForm = this.formBuilder.group({
      name: this.formBuilder.nonNullable.control('', [Validators.required]),
      description: this.formBuilder.nonNullable.control('', [Validators.required]),
      price: this.formBuilder.nonNullable.control(0, [Validators.required, Validators.min(1)]),
      allergens: this.formBuilder.nonNullable.control<string[]>([]),
    });

    this.updateFoodForm();

    this.foodService.getAllergens()
      .pipe(
        map(allergens => allergens.map(allergen => allergen.name))
      )
      .subscribe(allergens => this.allergens = allergens);
  }

  public ngOnChanges(changes: SimpleChanges): void {
    if (!changes['food'].isFirstChange()) {
      this.updateFoodForm();
    }
  }

  public handleAllergensChange(allergens: string[]) {
    this.foodForm.patchValue({
      allergens: allergens.filter(allergen => allergen.trim().length > 0)
    });
  }

  public handleSubmit() {
    this.foodForm.disable();
    this.request.subscribe({
      next: () => {
        this.foodForm.enable();
        if (this.isEditing) {
          this.submit.emit();
          this.close.emit();
          return;
        }

        void this.router.navigate([], {
          queryParams: {state: DashboardState.MODIFY_MENU}, queryParamsHandling: 'merge'
        })
      },
      error: (err: HttpErrorResponse) => {
        this.foodForm.enable();
        if (typeof err.error === 'string') {
          this.formError = err.error;
          return;
        }
        const errors = err.error as Record<string, string>;
        serverSideValidator(this.foodForm, errors);
      }
    });
  }

  public handleCancel() {
    if (this.isEditing) {
      this.close.emit();
    }

    void this.router.navigate([], {
      queryParams: {state: DashboardState.MODIFY_MENU}, queryParamsHandling: 'merge'
    })
  }

  private updateFoodForm() {
    this.foodForm.patchValue({
      name: this.food?.name ?? '',
      description: this.food?.description ?? '',
      price: this.food?.price ?? 0,
      allergens: this.food?.allergens.map(allergen => allergen.name) ?? []
    })
  }

  private get request() {
    if (this.isEditing && this.food) {
      return this.foodService.editFood(this.food.id, this.foodForm.value as CreateFoodRequest);
    }

    return this.foodService.createFood(this.foodForm.value as CreateFoodRequest)
  }

}
