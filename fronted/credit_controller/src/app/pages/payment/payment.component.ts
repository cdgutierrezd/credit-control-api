import { Component, inject } from '@angular/core';
import { ClientService } from '../../service/client/client.service';
import { ClientResponse } from '../../model/clients/client-response.model';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgSelectComponent } from '@ng-select/ng-select';
import { PaymentService } from '../../service/payment/payment.service';
import { SavePaymentRequest } from '../../model/payments/save-payment-request.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment',
  imports: [ReactiveFormsModule,NgSelectComponent],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent {
  private clientService = inject(ClientService);
  private paymentService = inject(PaymentService);
  private formBuilder = inject(FormBuilder);
  private router = inject(Router);
  clients:ClientResponse[] = [];

  ngOnInit():void{
    this.clientService.findAll().subscribe((data) => {
      this.clients = data;
    });
  }

  payForm = this.formBuilder.group({
    clientId:['',Validators.required],
    amount:['',[Validators.required,Validators.min(1)]]
  });

  toPay(){
    if(this.payForm.valid){
      const request:SavePaymentRequest = {
        clientId:parseInt(this.payForm.get('clientId')?.value ?? '0'),
        amount: parseInt(this.payForm.get('amount')?.value ?? '0')
      }
      this.paymentService.save(request).subscribe(() => {
        this.router.navigate(['/debts']);
      })
    }
  }
}
