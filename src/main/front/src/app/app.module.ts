import { BrowserModule } from '@angular/platform-browser';
import {NgModule, Provider} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { ListComponent } from './components/list/list.component';
import { DetailComponent } from './components/detail/detail.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {APP_BASE_HREF, DatePipe} from "@angular/common";
import {environment} from "../environments/environment";
import {AuthInterceptor} from "./auth.interceptor";
import { AuthComponent } from './components/auth/auth.component';

const INTERCEPTOR_PROVIDER: Provider = {
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptor,
  multi: true
};

@NgModule({
  declarations: [
    AppComponent,
    ListComponent,
    DetailComponent,
    AuthComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [INTERCEPTOR_PROVIDER],
  bootstrap: [AppComponent]
})
export class AppModule { }
