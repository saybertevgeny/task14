import {Injectable} from "@angular/core";
import {HttpClient, HttpEventType} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";
import {environment} from "../../environments/environment";

@Injectable({providedIn: 'root'})
export class ApiService {

  constructor(private httpClient: HttpClient) {
  }

  public getList(): Observable<any> {
    return this.httpClient.get(this.getApiPrefix() + 'api/list')
  }

  public getDetail(id): Observable<any> {
    return this.httpClient.get(this.getApiPrefix() + `/api/detail/${id}`)
  }

  public delete(): Observable<any> {
    return this.httpClient.delete(this.getApiPrefix() + 'api/delete', {
      observe: 'events'
    }).pipe(
      tap(event => {
        if (event.type == HttpEventType.Sent) {
          console.log('Sent', event)
        }
        if (event.type == HttpEventType.Response) {
          console.log('Response', event);
        }
      })
    );
  }

  private getApiPrefix(): string {
    return environment.apiUrl;
  }

}
