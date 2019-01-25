import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EditPersonComponent } from './edit-person/edit-person.component';
import { ListPersonComponent } from './list-person/list-person.component';

const routes: Routes = [
  {path : '', component: ListPersonComponent},
  {path : 'edit/:id', component: EditPersonComponent},
  {path : 'new', component: EditPersonComponent}
];

export const routing = RouterModule.forRoot(routes);
