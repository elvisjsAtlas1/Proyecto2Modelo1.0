import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {resources} from '../resources/resources';


@Injectable({providedIn: 'root'})
export class ApoderadoServiceService {
  constructor(private http: HttpClient) {
  }

  getApoderados(): Observable<any[]> {
    return this.http.get<any[]>(resources.apoderados.apoderado); // Se agregará la baseUrl por el interceptor
  }



}
