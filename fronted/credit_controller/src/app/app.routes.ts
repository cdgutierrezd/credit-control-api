import { Routes } from '@angular/router';
import { SaleComponent } from './pages/sale/sale.component';
import { PaymentComponent } from './pages/payment/payment.component';
import { RegisterClientComponent } from './pages/register-client/register-client.component';
import { DebtsComponent } from './pages/debts/debts.component';

export const routes: Routes = [
    {path:'', component:SaleComponent},
    {path:'payment',component:PaymentComponent},
    {path:'debts', component:DebtsComponent},
    {path:'register-client',component:RegisterClientComponent}
];
