import { Injectable } from '@angular/core';
import { environments } from '../../../../environments/environments';
import { API_ENDPOINTS } from '../../../../environments/API-paths';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  url: string = `${environments.API}/${API_ENDPOINTS.CONTRACT}`;

  constructor(private httpClient: HttpClient) { }

  testEndpoint():Observable<any>{
    return this.httpClient.get<any>(this.url+"/test");
  }

}
