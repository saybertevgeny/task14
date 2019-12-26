import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthManager} from "./service/auth.manager";
import {Injectable} from "@angular/core";
import {catchError, map, tap} from "rxjs/operators";

@Injectable({providedIn: 'root'})
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authManager: AuthManager) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.authManager.isAuth()) {
      req = req.clone({
        headers: req.headers.append('Authorization', 'Bearer ' + this.authManager.getToken())
      });
    }

    return next.handle(req).pipe(
      catchError((err) => {
        console.log(err);
        if (err instanceof HttpErrorResponse) {
          if (err.status == 401 || err.status == 0) {
            this.authManager.logout()
            location.reload();
            throw "Необходимо авторизоваться"
          }
        }
        throw "Ошибка загрузки";
      })
    );
  }
}
