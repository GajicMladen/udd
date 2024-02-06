import { Routes } from '@angular/router';
import { ContractComponent } from './features/contract/contract-page/contract.component';
import { ReportsComponent } from './features/reports/reports.component';
import { LawPageComponent } from './features/law/law-page/law-page.component';

export const routes: Routes = [
    {path:"",redirectTo:"contracts",pathMatch:"full"},
    {path:"contracts",component:ContractComponent},
    {path:"reports",component:ReportsComponent},
    {path:"laws",component:LawPageComponent}
];
