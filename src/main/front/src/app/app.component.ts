import {Component, OnInit} from '@angular/core';
import {ApiService} from "./service/api.service";
import {AuthManager} from "./service/auth.manager";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private apiService: ApiService, private authManager: AuthManager) {
  }

  ngOnInit(): void {
  }


  delete() {
  }

  public isAuth():boolean{
    return t
  }
}
