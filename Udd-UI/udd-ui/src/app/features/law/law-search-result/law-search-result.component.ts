import { Component, Input, inject } from '@angular/core';
import { Law } from '../../../model/law';
import { LawService } from '../../../shared/services/law-service/law.service';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-law-search-result',
  standalone: true,
  imports: [MatIconModule,MatButtonModule],
  templateUrl: './law-search-result.component.html',
  styleUrl: './law-search-result.component.css'
})
export class LawSearchResultComponent {

  @Input() law!:Law;

  lawService = inject(LawService);

  downloadFile(){
    this.lawService.downloadFile(this.law.serverFilename).subscribe(
      data =>{
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(data);
        link.download = this.law.serverFilename;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      }
    )

  }

}
