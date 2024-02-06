import { Routes } from '@angular/router';
import { ContractComponent } from './features/contract/contract-page/contract.component';
import { ReportsComponent } from './features/reports/reports.component';

export const routes: Routes = [
    {path:"",redirectTo:"contracts",pathMatch:"full"},
    {path:"contracts",component:ContractComponent},
    {path:"reports",component:ReportsComponent}
];
