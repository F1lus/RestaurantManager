import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'array',
  standalone: true
})
export class ArrayPipe<T extends Record<any, any>> implements PipeTransform {

  public transform(value: T[], property: string): string {
    if (value.length > 0 && value[0][property]) {
      return value.map(v => v[property]).join(', ');
    }

    return "-"
  }

}
