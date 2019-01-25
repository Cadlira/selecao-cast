import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonService } from '../services/person.service';
import { Person } from '../model/person.model';

@Component({
  selector: 'app-list-person',
  templateUrl: './list-person.component.html',
  styleUrls: ['./list-person.component.scss']
})
export class ListPersonComponent implements OnInit {

  people: Person[];

  constructor(private router: Router, private personService: PersonService) { }

  ngOnInit() {
    this.listPeople();
  }

  listPeople(): void {
    this.personService.getPeople()
      .subscribe( data => {
        this.people = data;
      });
  }

  deletePerson(person: Person): void {
    this.personService.deletePerson(person.id)
      .subscribe(data => {
        this.listPeople();
      });
  }

  editPerson(person: Person): void {
    this.router.navigate(['/edit', person.id]);
  }

  addPerson(): void {
    this.router.navigate(['/new']);
  }

}
