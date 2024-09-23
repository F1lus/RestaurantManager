import {Pipe, PipeTransform} from '@angular/core';
import {environment} from "../../environments/environment";

@Pipe({
  name: 'price',
  standalone: true
})
export class PricePipe implements PipeTransform {

  public transform(value: number, ...args: unknown[]): string {
    const groupedValue = this.formatNumber(value);
    return `${groupedValue} ${environment.priceTag}`;
  }

  private formatNumber(value: number) {
    return new Intl.NumberFormat('hu-HU', {
      minimumFractionDigits: 0,
      useGrouping: true
    }).format(value).replace(/,/g, ' ');
  }

}
