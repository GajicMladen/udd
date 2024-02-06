import { Component } from '@angular/core';
import { ContractService } from '../../../shared/services/contract-service/contract.service';
import { ToastrService } from 'ngx-toastr';
import { MatButtonModule } from '@angular/material/button';
import { UploadFileComponent } from '../../../shared/components/upload-file/upload-file.component';
import { API_ENDPOINTS } from '../../../../environments/API-paths';
import { environments } from '../../../../environments/environments';
import { ContractSearchComponent } from '../contract-search/contract-search.component';
import { ReportsComponent } from '../../reports/reports.component';

@Component({
  selector: 'app-contract',
  standalone: true,
  imports: [MatButtonModule,UploadFileComponent,ContractSearchComponent,ReportsComponent],
  templateUrl: './contract.component.html',
  styleUrl: './contract.component.css'
})
export class ContractComponent {

  addressToUpladFiles = environments.API+"/"+API_ENDPOINTS.CONTRACT+"/upload"

  constructor(
    private contractService:ContractService,
    private toastr: ToastrService
  ){

  }

  testEndpoint(){
    this.contractService.testEndpoint().subscribe(
      data =>{
        this.toastr.success(data);
      }
    )
  }

}
