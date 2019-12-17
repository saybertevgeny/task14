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
        alert("Ошибка загрузки")
      })
  }

  remove(id:number) {
    this.apiService.delete(id).subscribe(
      response => {
        this.todos.filter(todo => {return id != todo.id})
      },
      error => {
        alert("Ошибка при удалении");
      }
    )
  }
}
