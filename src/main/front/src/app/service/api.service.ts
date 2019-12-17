import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Todo} from "../dto/todo";

@Injectable({providedIn: 'root'})
export class ApiService {

  constructor(private httpClient: HttpClient) {
  }

  public getList(): Observable<any> {
    console.log(this.getApiPrefix() + 'api/list');
    try {
      return this.httpClient.get(this.getApiPrefix() + 'api/list')
    } catch (e) {
      console.log(e);
    }
  }

  public getDetail(id): Observable<any> {
    return this.httpClient.get(this.getApiPrefix() + `api/detail/${id}`)
  }

  public addTodo(todo: Todo): Observable<any> {
    return this.httpClient.post(this.getApiPrefix() + 'api/save', todo)
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete(this.getApiPrefix() + `api/delete/${id}`);
  }

  private getApiPrefix(): string {
    return environment.apiUrl;
  }

}
