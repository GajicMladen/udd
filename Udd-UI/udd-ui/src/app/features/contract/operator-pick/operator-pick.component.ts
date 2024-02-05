import { Component, Input } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-operator-pick',
  standalone: true,
  imports: [MatFormFieldModule,MatSelectModule,ReactiveFormsModule],
  templateUrl: './operator-pick.component.html',
  styleUrl: './operator-pick.component.css'
})
export class OperatorPickComponent {

  @Input() formGroupInput!: FormGroup;
  @Input() formControlNameInput : string = "";

}
