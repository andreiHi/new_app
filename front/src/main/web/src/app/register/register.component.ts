import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../_services/alert.service';
import { UserService } from '../_services/user.service';
import { AuthenticationService } from '../_services/authentication.service';
import { first } from 'rxjs/operators';
import { MustMatch } from '../_helpers/must-match.validators';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    registerForm: FormGroup;
    loading = false;
    submitted = false;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authenticationService: AuthenticationService,
        private userService: UserService,
        private alertService: AlertService) {
        if (this.authenticationService.currentUserValue) {
            this.router.navigate(['/']);
        }
    }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            login: ['', Validators.required],
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required, Validators.minLength(6)]],
            confirmedPassword: ['', [Validators.required, Validators.minLength(6)]]
        }, {validator: MustMatch('password', 'confirmedPassword')});

    }

    get f() {
        return this.registerForm.controls;
    }


    onSubmit() {
        this.submitted = true;
        if (this.registerForm.invalid) {
            return
        }
        this.loading = true;
        console.log(this.registerForm.value);
        this.userService.register(this.registerForm.value)
            .pipe(first())
            .subscribe(data =>{
                this.alertService.success('Registration successful', true);
            },
                error =>{
                this.alertService.error(error);
                });
    }
}
