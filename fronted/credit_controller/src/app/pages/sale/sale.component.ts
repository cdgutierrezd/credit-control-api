import { Component, inject,OnInit } from '@angular/core';
import { ClientService } from '../../service/client/client.service';
import { ClientResponse } from '../../model/clients/client-response.model';
import { NgSelectComponent } from '@ng-select/ng-select';
import { ReactiveFormsModule,FormBuilder, Validators } from '@angular/forms';
import { SaleService } from '../../service/sale/sale.service';
import { SaveSaleRequest } from '../../model/sales/save-sale-request.model';
import { Router } from '@angular/router';
@Component({
  selector: 'app-sale',
  imports: [NgSelectComponent,ReactiveFormsModule],
  templateUrl: './sale.component.html',
  styleUrl: './sale.component.css'
})
export class SaleComponent {
  private clientService = inject(ClientService);
  private saleService = inject(SaleService);
  private formBuilder = inject(FormBuilder);
  private router = inject(Router);
  clients:ClientResponse[] = [];



  saleForm = this.formBuilder.group({
    clientId:['',Validators.required],
    amount:['',[Validators.required,Validators.min(1)]],
    description:['']
  });

  ngOnInit():void{
    this.clientService.findAll().subscribe((data) => {
      this.clients = data;
    });
  }

  saveSale(){
    if(this.saleForm.valid){
      const data = this.saleForm.value;
      
      const amountInt = parseInt(data.amount!);

      const request:SaveSaleRequest = {
        clientId: parseInt(data.clientId!),
        amount: amountInt,
        description:data.description ?? ''
      }

      this.saleService.save(request).subscribe((data) => {
        this.router.navigate(['/debts']);
      })

    }
  }
}