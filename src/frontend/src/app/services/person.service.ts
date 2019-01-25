import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Person } from '../model/person.model';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  baseUrl: string = location.origin + '/rest/';

  constructor(private http: HttpClient) {}

  getPeople() {
    return this.http.get<Person[]>(this.baseUrl + 'pessoas');
  }

  getPersonById(id: number) {
    return this.http.get<Person>(this.baseUrl + 'pessoa/' + id);
  }

  savePerson(person: Person) {
    return this.http.post(this.baseUrl + 'pessoa/save/', person);
  }

  deletePerson(id: number) {
    return this.http.delete(this.baseUrl + 'pessoa/remove/' + id);
  }

}
