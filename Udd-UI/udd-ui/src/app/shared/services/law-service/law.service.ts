import { Injectable } from '@angular/core';
import { environments } from '../../../../environments/environments';
import { API_ENDPOINTS } from '../../../../environments/API-paths';
import { HttpClient } from '@angular/common/http';
import { Law } from '../../../model/law';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LawService {

  url: string = `${environments.API}/${API_ENDPOINTS.LAW}`;

  constructor(private httpClient: HttpClient) { }

  searchLawsByContent(content:string):Observable<Law[]>{
    let formData = new FormData();
    formData.append("content",content);
    return this.httpClient.post<Law[]>(this.url+"/findByContent",formData);
  }
  
  downloadFile(filename:string):Observable<any>{
    let formData = new FormData();
    formData.append("filename",filename)
    return this.httpClient.post(this.url+"/download",formData,{responseType:'blob'});
  }
}
