import { Component, inject } from '@angular/core';
import { ClientService } from '../../service/client/client.service';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { SaveClientRequest } from '../../model/clients/save-client-request.model';

@Component({
  selector: 'app-register-client',
  imports: [ReactiveFormsModule],
  templateUrl: './register-client.component.html',
  styleUrl: './register-client.component.css'
})
export class RegisterClientComponent {

  private clientService = inject(ClientService);
  private formBuilder = inject(FormBuilder);

  saveClientForm = this.formBuilder.group({
    name:['',Validators.required],
    address:['',Validators.required]
  });
  
  saveClient(){
    if(this.saveClientForm.valid){
      const request:SaveClientRequest = {
        name: this.saveClientForm.get('name')!.value ?? '',
        address: this.saveClientForm.get('address')!.value ?? ''
      }
      this.clientService.save(request).subscribe((data) => {
        alert(`Clients save`);
      });
    }
    
  }

}
