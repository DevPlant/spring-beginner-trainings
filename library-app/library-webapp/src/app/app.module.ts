import {BrowserModule} from "@angular/platform-browser";
import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {MaterialModule} from "@angular/material";
import {FlexLayoutModule} from "@angular/flex-layout";
import {AppComponent} from "./app.component";
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {LibraryComponent} from "./library/library.component";
import {RouteModule} from "./route/route.module";
import {AccountAlreadyActivatedComponent} from "./account-already-activated/account-already-activated.component";
import {AccountActivationFailedComponent} from "./account-activation-failed/account-activation-failed.component";
import {AccountActivationSuccessfulComponent} from "./account-activation-successful/account-activation-successful.component";
import {ActivationParentComponent} from "./activation-parent/activation-parent.component";
import {HomeComponent} from "./home/home.component";
import {RegisterGuard} from "./route/guard/register.guard";
import {LoginGuard} from "./route/guard/login.guard";
import {UserService} from "./user/user.service";
import {BookDetailsComponent} from "./book-details/book-details.component";
import {Md2Module} from "md2";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ReservationManagementComponent} from "./reservation-management/reservation-management.component";
import {AdminGuard} from "./route/guard/admin.guard";


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    LibraryComponent,
    AccountAlreadyActivatedComponent,
    AccountActivationFailedComponent,
    AccountActivationSuccessfulComponent,
    ActivationParentComponent,
    HomeComponent,
    BookDetailsComponent,
    ReservationManagementComponent,
  ],
  imports: [
    Md2Module.forRoot(),
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    RouteModule,
    FlexLayoutModule,
    MaterialModule,
    ReactiveFormsModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [LoginGuard, RegisterGuard, AdminGuard, UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor (private userService: UserService) {
    userService.initialize();
  }
}


