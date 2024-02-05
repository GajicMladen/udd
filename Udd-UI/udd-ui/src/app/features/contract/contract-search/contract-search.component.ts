import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule} from '@angular/material/select';
import { MatSlideToggleModule } from "@angular/material/slide-toggle";
import { OperatorPickComponent } from '../operator-pick/operator-pick.component';
import { ContractService } from '../../../shared/services/contract-service/contract.service';
import { Contract } from '../../../model/contract';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ContractSearchResultComponent } from '../contract-search-result/contract-search-result.component';
import { error } from 'console';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-contract-search',
  standalone: true,
  imports: [MatSlideToggleModule,MatButtonModule,MatIconModule,
    MatInputModule,MatFormFieldModule,OperatorPickComponent,ReactiveFormsModule,ContractSearchResultComponent,MatIconModule],
  templateUrl: './contract-search.component.html',
  styleUrl: './contract-search.component.css'
})
export class ContractSearchComponent {

  form : FormGroup = new FormGroup({
    nameAndLastnameSignAgency: new FormControl(""),
    contractContent : new FormControl(""),
    govNameAndLevel : new FormControl(""),
    nameAndLastnameSignAgencyOperator: new FormControl("OR"),
    contractContentOperator : new FormControl("OR"),
    govNameAndLevelOperator : new FormControl("OR"),
    B_nameAndLastnameSignAgency: new FormControl(false),
    B_contractContent : new FormControl(false),
    B_govNameAndLevel : new FormControl(false),
    address: new FormControl(""),
    distance: new FormControl("")
  })

  geoLocationSearch:boolean = false;
  
  resultOfSearch: Contract[] = [];
  
  contractService = inject(ContractService);

  isMoreThanOneSelected():boolean{
    let i: number = 0;
    if(this.form.get("B_contractContent")?.value) i++;
    if(this.form.get("B_govNameAndLevel")?.value)i++;
    if(this.form.get("B_nameAndLastnameSignAgency")?.value) i++;

    return i > 1;
  }

  enablelGeoLocationSearch(){
    this.geoLocationSearch = !this.geoLocationSearch;
    if(this.geoLocationSearch){
      this.form.get("B_contractContent")?.setValue(false);
      this.form.get("B_govNameAndLevel")?.setValue(false);
      this.form.get("B_nameAndLastnameSignAgency")?.setValue(false);
    }
  }

  searchContracts(){
    if(!this.geoLocationSearch){
      if(!this.isMoreThanOneSelected()){
        if(this.form.get("B_contractContent")?.value)
          this.contractService.searchContractByContent(this.form.get("contractContent")?.value).subscribe(data => this.resultOfSearch = data);
        else if(this.form.get("B_govNameAndLevel")?.value)
          this.contractService.searchContractByNameAndLevelGov(this.form.get("govNameAndLevel")?.value).subscribe(data=> this.resultOfSearch=data);
        else if( this.form.get("B_nameAndLastnameSignAgency")?.value)
          this.contractService.searchContractByNameAndLastnameSignAgency(this.form.get("nameAndLastnameSignAgency")?.value).subscribe(data=> this.resultOfSearch=data);
      }
      else{
        this.contractService.advancedSearch(this.form).subscribe(data=> this.resultOfSearch=data);
      }
    }else{
      this.contractService.geoLocationSearch(this.form.get("address")?.value,this.form.get("distance")?.value).subscribe(data => this.resultOfSearch=data);
    }
  }
}
