import { Injectable } from '@angular/core';
import { environments } from '../../../../environments/environments';
import { API_ENDPOINTS } from '../../../../environments/API-paths';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contract } from '../../../model/contract';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  url: string = `${environments.API}/${API_ENDPOINTS.CONTRACT}`;

  constructor(private httpClient: HttpClient) { }

  testEndpoint():Observable<any>{
    return this.httpClient.get<any>(this.url+"/test");
  }

  searchContractByTitle(title:string):Observable<Contract[]>{
    let formData = new FormData();
    formData.append("title",title);
    return this.httpClient.post<Contract[]>(this.url+"/findByTitle",formData);
  }

  searchContractByNameAndLastnameSignAgency(nameAndLastnameSignAgency:string):Observable<Contract[]>{
    let formData = new FormData();
    formData.append("nameAndLastName",nameAndLastnameSignAgency);
    return this.httpClient.post<Contract[]>(this.url+"/findByNameAndLastnameSignAgency",formData);
  }
  

  searchContractByNameAndLevelGov(nameAndLevelGov:string):Observable<Contract[]>{
    let formData = new FormData();
    formData.append("nameAndLevel",nameAndLevelGov);
    return this.httpClient.post<Contract[]>(this.url+"/findByNameAndLevelGov",formData);
  }
  

  searchContractByContent(content:string):Observable<Contract[]>{
    let formData = new FormData();
    formData.append("content",content);
    return this.httpClient.post<Contract[]>(this.url+"/findByContent",formData);
  }

  advancedSearch(form:FormGroup):Observable<Contract[]>{
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.post<Contract[]>(this.url+"/advancedSearch",JSON.stringify(form.getRawValue()),
                                              {headers:headers});
  }

  geoLocationSearch(address:string,distance:string):Observable<Contract[]>{
    let formData = new FormData();
    formData.append("address",address);
    formData.append("distance",distance);
    return this.httpClient.post<Contract[]>(this.url+"/geoLocationSearch",formData);
  }

  downloadFile(filename:string):Observable<any>{
    let formData = new FormData();
    formData.append("filename",filename)
    return this.httpClient.post(this.url+"/download",formData,{responseType:'blob'});
  }
}
