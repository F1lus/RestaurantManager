import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'error',
  standalone: true
})
export class ErrorPipe implements PipeTransform {

  transform(value: any): string {
    if (!value || !value.serverSide) {
      return "";
    }

    const errorKeys = Object.keys(value.serverSide);
    if (errorKeys.length === 0) {
      return "";
    }

    return errorKeys[0];
  }

}
