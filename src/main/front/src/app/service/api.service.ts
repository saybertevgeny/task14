import {Injectable} from "@angular/core";
import {HttpClient, HttpEventType} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";

@Injectable({providedIn: 'root'})
export class ApiService{

  constructor(private httpClient: HttpClient){}

  public getList():Observable<any>{
    return this.httpClient.get('http://localhost:8080/task14/api/list')
  }

  public getDetail(id):Observable<any>{
    return this.httpClient.get(`http://localhost:8080/task14/api/detail/${id}`)
  }

  public delete():Observable<any>{
    return this.httpClient.delete('http://localhost:8080/task14/api/delete',{
      observe: 'events'
    }).pipe(
      tap(event => {
        if(event.type == HttpEventType.Sent){
          console.log('Sent',event)
        }
        if(event.type == HttpEventType.Response){
          console.log('Response',event);
        }
      })
    );
  }

}
