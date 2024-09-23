import {AbstractControl, FormGroup} from "@angular/forms";

type K<T> = { [K in keyof T]: AbstractControl<any, any>; };

export function serverSideValidator<T extends K<T>>(form: FormGroup<T>, errors: string[]) {

}
