import { Component, inject } from '@angular/core';
import { environments } from '../../../../environments/environments';
import { API_ENDPOINTS } from '../../../../environments/API-paths';
import { UploadFileComponent } from '../../../shared/components/upload-file/upload-file.component';
import { LawSearchResultComponent } from '../law-search-result/law-search-result.component';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { Law } from '../../../model/law';
import { LawService } from '../../../shared/services/law-service/law.service';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-law-page',
  standalone: true,
  imports: [UploadFileComponent,LawSearchResultComponent,ReactiveFormsModule,
    MatFormFieldModule,MatIconModule,MatInputModule,MatButtonModule],
  templateUrl: './law-page.component.html',
  styleUrl: './law-page.component.css'
})
export class LawPageComponent {

  addressToUpladFiles = environments.API+"/"+API_ENDPOINTS.LAW+"/upload"

  resultOfSearch: Law[] = [];

  lawService = inject(LawService);

  form: FormGroup = new FormGroup({
    content:new FormControl("")
  })

  
  searchContracts(){
    this.lawService.searchLawsByContent(this.form.get("content")?.value).subscribe(data => this.resultOfSearch=data);
  }

}
