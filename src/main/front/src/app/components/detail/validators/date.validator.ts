import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {formatDate} from "@angular/common";
import {LOCALE_ID} from '@angular/core';


export class DateValidator {
  static inFuture(control: FormControl): { [key: string]: boolean } {
    let date: Date = new Date(Date.parse(control.value));
    date.setHours(0);
    date.setMinutes(0);
    date.setSeconds(0);
    date.setMilliseconds(0);
    let dateNow: Date = new Date(Date.now());
    dateNow.setHours(0);
    dateNow.setMinutes(0);
    dateNow.setSeconds(0);
    dateNow.setMilliseconds(0);
    if (date.getTime() < dateNow.getTime()) {
      return {
        dataInFuture: true
      }
    }
    return null;
  }

}

