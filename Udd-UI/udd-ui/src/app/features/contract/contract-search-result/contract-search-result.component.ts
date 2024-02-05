import { Component, Input } from '@angular/core';
import { Contract } from '../../../model/contract';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-contract-search-result',
  standalone: true,
  imports: [MatButtonModule,MatIconModule],
  templateUrl: './contract-search-result.component.html',
  styleUrl: './contract-search-result.component.css'
})
export class ContractSearchResultComponent {

  @Input() contract!: Contract;
}
