import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SaleResponse } from '../../model/sales/sale-response.model';
import { SaveSaleRequest } from '../../model/sales/save-sale-request.model';

@Injectable({
  providedIn: 'root'
})
export class SaleService {
  private url = 'http://localhost:8080/api/sales';
  private http = inject(HttpClient);

  constructor() { }

  findAll():Observable<SaleResponse[]>{
    return this.http.get<SaleResponse[]>(this.url);
  }

  findById(id:number):Observable<SaleResponse>{
    const urlId = `${this.url}/${id}`;
    return this.http.get<SaleResponse>(urlId);
  }

  save(request:SaveSaleRequest):Observable<SaleResponse>{
    return this.http.post<SaleResponse>(this.url,request);
  }

  update(id:number,request:SaveSaleRequest):Observable<SaleResponse>{
    const urlId = `${this.url}/${id}`;
    return this.http.put<SaleResponse>(urlId,request);
  }

  delete(id:number):Observable<void>{
    const urlId = `${this.url}/${id}`;
    return this.http.delete<void>(urlId);
  }
}
