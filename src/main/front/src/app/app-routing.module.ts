import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ListComponent} from "./components/list/list.component";
import {DetailComponent} from "./components/detail/detail.component";


const routes: Routes = [
  {path:'',component: ListComponent},
  {path:'update/:id', component: DetailComponent},
  {path:'add', component: DetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
