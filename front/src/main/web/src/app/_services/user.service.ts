import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../_models/user';
import { Observable } from 'rxjs';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class UserService {
SERVER_API_URL = 'http://localhost:8090';
  constructor(private http: HttpClient) { }

    register(user: User): Observable<any> {
      return this.http.post(this.SERVER_API_URL + '/api/register', user, httpOptions);
    }
}
