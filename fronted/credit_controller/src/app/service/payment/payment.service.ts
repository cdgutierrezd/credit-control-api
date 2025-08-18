import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SavePaymentRequest } from '../../model/payments/save-payment-request.model';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private url = 'http://localhost:8080/api/payments';
  private http = inject(HttpClient);

  constructor() { }

  findAll():Observable<PaymentResponse[]>{
    return this.http.get<PaymentResponse[]>(this.url);
  }

  findById(id:number):Observable<PaymentResponse>{
    const urlId = `${this.url}/${id}`;
    return this.http.get<PaymentResponse>(urlId);
  }

  save(request:SavePaymentRequest):Observable<PaymentResponse>{
    return this.http.post<PaymentResponse>(this.url,request);
  }

  update(id:number,request:SavePaymentRequest):Observable<PaymentResponse>{
    const urlId = `${this.url}/${id}`;
    return this.http.put<PaymentResponse>(urlId,request);
  }

  delete(id:number):Observable<void>{
    const urlId = `${this.url}/${id}`;
    return this.http.delete<void>(urlId);
  }
}
