import {Component, OnInit} from '@angular/core';
import {ApiService} from "../../service/api.service";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Todo} from '../../dto/todo';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";

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

  constructor(private apiService: ApiService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      if (Object.keys(params).length == 0) {
        this.updating = false;
      } else {
        this.loading = true;
        this.apiService.getDetail(+params.id).subscribe(response => {
          this.updating = true;
          this.todo = response;
          this.loading = false;
        })
      }
      this.initForm();
    });
  }

  submit() {
    if (this.form.valid) {
      console.log('Form:', this.form);
      const formData = {...this.form.value};
      console.log('Form data: ', formData);
      this.form.reset();
    }
  }

  private initForm() {
    this.form = new FormGroup({
      theme: new FormControl('',
        [Validators.required]),
      text: new FormControl(null, [Validators.required]),
      deadline: new FormControl(null, [Validators.required]),
      priority: new FormControl(null, [Validators.required]),
      id: new FormControl(this.updating ? this.todo.id : null, [])
    });
  }
}
