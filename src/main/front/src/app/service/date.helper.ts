import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class DateHelper {

  public toDefaultFromDate(date:Object){
    let day = date['day'] < 10 ? '0'+date['day'] : date['day'];
    let month = date['month'] < 10 ? '0'+date['month'] : date['month'];
    return `${date['year']}-${month}-${day}`;
  }

  public toDateFromDefault(dateStr:string ) {
    let matches:string[] = dateStr.match(/([0-9]{2}).([0-9]{2}).([0-9]{4})/);
    let year:number = parseInt(matches[3]);
    let month:number = parseInt(matches[2]);
    let day:number = parseInt(matches[1]);
    return {year,month,day};
  }
}


