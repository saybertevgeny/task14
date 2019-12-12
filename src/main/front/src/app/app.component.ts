import {Component, OnInit} from '@angular/core';
import {ApiService} from "./service/api.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
  }


  delete() {
    this.apiService.delete().subscribe(response=>{
      console.log(response);
    })
  }
}
