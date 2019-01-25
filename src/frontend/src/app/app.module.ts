import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { routing } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListPersonComponent } from './list-person/list-person.component';
import { EditPersonComponent } from './edit-person/edit-person.component';
import { PersonService } from './services/person.service';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    ListPersonComponent,
    EditPersonComponent
  ],
  imports: [
    BrowserModule,
    routing,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [PersonService],
  bootstrap: [AppComponent]
})
export class AppModule { }
