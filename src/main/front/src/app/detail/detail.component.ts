import {Component, OnInit} from '@angular/core';
import {ApiService} from "../service/api.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Todo} from '../dto/todo';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  loading: boolean = false;
  todo: Todo;

  constructor(private apiService: ApiService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.loading = true;
      this.apiService.getDetail(+params.id).subscribe(response => {
        this.todo = response;
        this.loading = false;
      })
    });


  }

}
