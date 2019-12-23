import {Injectable} from "@angular/core";
import {ApiService} from "./api.service";

@Injectable({providedIn: 'root'})
export class AuthManager {
  private static readonly AUTH_TOKEN_KEY = "app.token";

  constructor(private apiService:ApiService){}

  public isAuth(): boolean {
    let token: string = "";
    token = sessionStorage.getItem(AuthManager.AUTH_TOKEN_KEY);
    return token == null;
  }

  public auth(username:string,password:string){
    this.apiService.auth({username,password}).subscribe(response =>{
      console.log(response)
    });
  }
}
