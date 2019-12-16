import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../service/api.service";
import {Todo} from '../../dto/todo';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  todos: Todo[];
  loading: boolean = false;

  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    this.loading = true;
    this.apiService.getList().subscribe(response => {
        this.todos = response;
        this.loading = false
      },
      error => {
        console.log(error);
      })
  }

}
