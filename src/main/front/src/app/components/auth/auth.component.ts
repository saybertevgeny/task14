import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {LoginRequest} from "../../dto/login.request";
import {AuthManager} from "../../service/auth.manager";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  form: FormGroup;
  error: String;

  constructor(private authManager: AuthManager) {
  }

  ngOnInit() {
    this.form = new FormGroup({
      login: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

  submit() {
    if (this.form.valid) {
      let loginRequest: LoginRequest = {
        login: this.form.get('login').value,
        password: this.form.get('password').value
      }
      this.authManager.auth(loginRequest).subscribe(
        token => {
          location.reload()
        },
        error => {
          console.log(error);
        });
    }
  }

  clearError() {
    this.error = '';
  }
}
