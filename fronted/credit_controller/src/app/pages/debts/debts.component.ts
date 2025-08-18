import { Component, inject } from '@angular/core';
import { ClientService } from '../../service/client/client.service';
import { ClientResponse } from '../../model/clients/client-response.model';

@Component({
  selector: 'app-debts',
  imports: [],
  templateUrl: './debts.component.html',
  styleUrl: './debts.component.css'
})
export class DebtsComponent {
  private clientService = inject(ClientService);
  clients:ClientResponse[] = [];
  ngOnInit():void{
    this.clientService.findByDebt().subscribe((data) => {
      this.clients = data;
    });
  }
}
