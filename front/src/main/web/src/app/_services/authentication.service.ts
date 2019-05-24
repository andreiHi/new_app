import { Injectable } from '@angular/core';
import { User } from '../_models/user';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;

  constructor() { }

    public get currentUserValue(): User {
      // return this.currentUserSubject.value;
        return ;
    }
}
