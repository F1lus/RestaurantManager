import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'array',
  standalone: true
})
export class ArrayPipe implements PipeTransform {

  public transform(value: any, property: string): string {
    if (!Array.isArray(value)) {
      return "" + value;
    }

    if (value.length > 0 && value[0]) {
      return value.map(v => this.getValue(v, property)).join(', ');
    }

    return "-"
  }

  private getValue(value: any, property: string): string {
    switch (property) {
      case 'allergens':
        return value['name'];
      case 'profileTypes':
        return value;
      default:
        return value[property];
    }
  }

}
