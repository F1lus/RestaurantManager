import {AbstractControl, FormGroup} from "@angular/forms";

type K<T> = { [K in keyof T]: AbstractControl<any, any>; };

export function serverSideValidator<T extends K<T>>(form: FormGroup<T>, errors: Record<string, string>) {
  const errorList = Object.entries(errors);
  if (errorList.length === 0) return;

  errorList.forEach(([key, value]) => {
    form.get(key)?.setErrors({serverSide: {[value]: true}})
  })
}
