import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../service/api.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Todo} from '../../dto/todo';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DateValidator} from "./validators/date.validator";
import {DateHelper} from "../../service/date.helper";

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {

  private updating: boolean = false;
  loading: boolean = false;
  todo: Todo;
  form: FormGroup;

  constructor(
    private apiService: ApiService,
    private route: ActivatedRoute,
    private router: Router,
    private dataHelper: DateHelper
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      if (Object.keys(params).length == 0) {
        this.updating = false;
        this.initForm();
      } else {
        this.loading = true;
        this.apiService.getDetail(+params.id).subscribe(response => {
            this.updating = true;
            this.todo = response;
            this.loading = false;
            this.initForm();
          },
          error => {
            alert("Ошибка при загрузке");
          })
      }

    });
  }

  submit() {
    if (this.form.valid) {
      let todo: Todo = {
        id: this.form.get("id").value,
        theme: this.form.get("theme").value,
        text: this.form.get("text").value,
        priority: this.form.get("priority").value,
        deadline: this.form.get("deadline").value
      };
      this.apiService.addTodo(todo).subscribe(
        response => {
          if (!this.updating) {
            this.form.reset();
          }
        },
        error => {
          alert("Ошибка при сохраненнии.")
        });
    }
  }

  private initForm() {
    this.form = new FormGroup({
      theme: new FormControl(this.updating ? this.todo.theme : null,
        [Validators.required]),
      text: new FormControl(this.updating ? this.todo.text : null, [Validators.required]),
      deadline: new FormControl(this.updating ? this.dataHelper.getDefaultFormatedDate(this.todo.deadline) : null, [Validators.required, DateValidator.inFuture]),
      priority: new FormControl(this.updating ? this.todo.priority : null, [Validators.required]),
      id: new FormControl(this.updating ? this.todo.id : null, [])
    });
  }
}
