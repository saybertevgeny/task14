import {Injectable} from "@angular/core";
import {HttpClient, HttpEventType} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";
import {environment} from "../../environments/environment";
import {Todo} from "../dto/todo";

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

  public addTodo(todo:Todo): Observable<any>{
    return this.httpClient.post(this.getApiPrefix() + 'api/save',todo)
  }

  private getApiPrefix(): string {
    return environment.apiUrl;
  }

}
