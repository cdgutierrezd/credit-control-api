import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ClientResponse } from '../../model/clients/client-response.model';
import { Observable } from 'rxjs';
import { SaveClientRequest } from '../../model/clients/save-client-request.model';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private url = 'http://localhost:8080/api/clients';
  private http = inject(HttpClient);
  
  
  constructor() { }

  findAll():Observable<ClientResponse[]>{
    return this.http.get<ClientResponse[]>(this.url);
  }

  findByDebt():Observable<ClientResponse[]>{
    const urlDebt = `${this.url}/debts`;
    return this.http.get<ClientResponse[]>(urlDebt);
  }

  findById(id:number):Observable<ClientResponse>{
    const urlId = `${this.url}/${id}`;
    return this.http.get<ClientResponse>(urlId);
  }

  save(request:SaveClientRequest):Observable<ClientResponse>{
    return this.http.post<ClientResponse>(this.url,request);
  }

  update(id:number,request:SaveClientRequest):Observable<ClientResponse>{
    const urlId = `${this.url}/${id}`;
    return this.http.put<ClientResponse>(urlId,request);
  }

  delete(id:number):Observable<void>{
    const urlId = `${this.url}/${id}`;
    return this.http.delete<void>(urlId);
  }
}
