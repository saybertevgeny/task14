import {Injectable} from "@angular/core";
import {ApiService} from "./api.service";
import {LoginRequest} from "../dto/login.request";
import {map} from "rxjs/operators";
import {Observable} from "rxjs";
import {isNull} from "util";

@Injectable({providedIn: 'root'})
export class AuthManager {
  private static readonly AUTH_TOKEN_KEY = "app.token";

  constructor(private apiService: ApiService) {}

  public isAuth(): boolean {
    let token:string = JSON.parse(sessionStorage.getItem(AuthManager.AUTH_TOKEN_KEY));
    return !isNull(token);
  }

  public getToken(){
    return JSON.parse(sessionStorage.getItem(AuthManager.AUTH_TOKEN_KEY));
  }

  public auth(loginRequest: LoginRequest): Observable<any> {
    return this.apiService.auth(loginRequest).pipe(
      map(response => {
        sessionStorage.setItem(AuthManager.AUTH_TOKEN_KEY,JSON.stringify(response.token))
        return response.token;
      })
    );
  }

  logout() {
    sessionStorage.setItem(AuthManager.AUTH_TOKEN_KEY,null);
  }
}
