import {AbstractControl, ValidatorFn} from "@angular/forms";
import {DateTime} from "luxon";

export function reservationTimeValidation(): ValidatorFn {
  return (formGroup: AbstractControl) => {
    if (!formGroup.get('reservationStart')?.value || !formGroup.get('reservationEnd')?.value) {
      return null;
    }

    const start = DateTime.fromISO(formGroup.get('reservationStart')!.value);
    const end = DateTime.fromISO(formGroup.get('reservationEnd')!.value);

    if (start.toMillis() >= end.toMillis()) {
      return {startAfterEnd: true};
    }

    const diff = end.diff(start, ['hours', 'minutes']);

    if (diff.minutes < 30 && diff.hours === 0) {
      return {minLimit: true};
    }

    if (diff.hours > 4) {
      return {maxLimit: true};
    }

    return null;
  }
}
