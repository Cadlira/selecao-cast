import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PersonService } from '../services/person.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-edit-person',
  templateUrl: './edit-person.component.html',
  styleUrls: ['./edit-person.component.scss']
})
export class EditPersonComponent implements OnInit {

  personId: any = -1;
  personForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router, private personService: PersonService
            , private route: ActivatedRoute) { }

  ngOnInit() {
    this.personForm = this.formBuilder.group({
      id: [0],
      name: ['', Validators.required],
      phone: [],
      cellphone: [],
      street: [],
      number: [],
      neighborhood: [],
      city: [],
      state: []
    });

    if (this.route.snapshot.params['id']) {
      this.personId = this.route.snapshot.params['id'];
      this.personService.getPersonById(this.personId)
        .subscribe(data => {
          this.personForm.setValue(data);
        });
    }
  }

  onSubmit() {
    this.personService.savePerson(this.personForm.value)
      .subscribe (data => {
        this.router.navigate(['/']);
      },
      error => {
        alert(error);
        this.router.navigate(['/']);
      });
  }

}
