import { Routes } from '@angular/router';
import { ContractComponent } from './features/contract/contract.component';

export const routes: Routes = [
    {path:"",redirectTo:"contract",pathMatch:"full"},
    {path:"contract",component:ContractComponent}
];
