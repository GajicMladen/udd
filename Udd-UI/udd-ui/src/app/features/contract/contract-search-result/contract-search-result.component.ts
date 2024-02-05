import { Component, Input, inject } from '@angular/core';
import { Contract } from '../../../model/contract';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ContractService } from '../../../shared/services/contract-service/contract.service';

@Component({
  selector: 'app-contract-search-result',
  standalone: true,
  imports: [MatButtonModule,MatIconModule],
  templateUrl: './contract-search-result.component.html',
  styleUrl: './contract-search-result.component.css'
})
export class ContractSearchResultComponent {

  @Input() contract!: Contract;

  contractService:ContractService = inject(ContractService);

  downloadFile(){
    this.contractService.downloadFile(this.contract.serverFilename).subscribe(
      data =>{
        const link = document.createElement('a');
        link.href = window.URL.createObjectURL(data);
        link.download = this.contract.serverFilename;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      }
    )
  }
}
