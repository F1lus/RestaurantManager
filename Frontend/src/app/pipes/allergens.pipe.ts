import {Pipe, PipeTransform} from '@angular/core';
import {Allergen} from "../model/common";

@Pipe({
  name: 'allergens',
  standalone: true
})
export class AllergensPipe implements PipeTransform {

  public transform(value: Allergen[]): string {
    if (value.length > 0) {
      return value.map(allergen => allergen.name).join(', ');
    }

    return "-"
  }

}
