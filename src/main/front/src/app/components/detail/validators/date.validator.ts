import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {formatDate} from "@angular/common";
import {LOCALE_ID} from '@angular/core';
import {isObject} from "util";


export class DateValidator {

  static validate(control: FormControl) {
    if (isObject(control.value)) {
      if (control.value['year'] != null && control.value['month'] != null && control.value['day'] != null){
        return null;
      }
    }
    return {
      validateDate: true
    }
  }
}

