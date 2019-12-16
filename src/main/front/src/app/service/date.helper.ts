import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class DateHelper {

   public getDefaultFormatedDate(ruFormatedDate:string ) {
     let matches:string[] = ruFormatedDate.match(/([0-9]{2}).([0-9]{2}).([0-9]{4})/);
     return `${matches[3]}-${matches[2]}-${matches[1]}`;
  }
}


