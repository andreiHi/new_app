import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';
import { ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SearchComponent } from './search/search.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AlertComponent } from './alert/alert.component';

@NgModule({
    declarations: [
        AppComponent,
        SearchComponent,
        RegisterComponent,
        LoginComponent,
        AlertComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        ReactiveFormsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
