import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import {LoginComponent} from "../login/login.component";
import {RegisterComponent} from "../register/register.component";
import {LibraryComponent} from "../library/library.component";
import {AccountActivationSuccessfulComponent} from "../account-activation-successful/account-activation-successful.component";
import {AccountActivationFailedComponent} from "../account-activation-failed/account-activation-failed.component";
import {AccountAlreadyActivatedComponent} from "../account-already-activated/account-already-activated.component";
import {ActivationParentComponent} from "../activation-parent/activation-parent.component";
import {HomeComponent} from "../home/home.component";
import {LoginGuard} from "./guard/login.guard";
import {RegisterGuard} from "./guard/register.guard";
import {BookDetailsComponent} from "../book-details/book-details.component";
import {ReservationManagementComponent} from "../reservation-management/reservation-management.component";
import {AdminGuard} from "./guard/admin.guard";

const routes: Routes = [
  {
    path: '',
    redirectTo: '/library',
    pathMatch: 'full'
  },
  {
    path: 'library',
    component: LibraryComponent,
    children: [
      {
        path: '',
        component: HomeComponent,
      },
      {
        path: 'details/:bookId',
        component: BookDetailsComponent,
      },
      {
        path: 'login',
        canActivate: [LoginGuard],
        component: LoginComponent,
      },
      {
        path: 'register',
        canActivate: [RegisterGuard],
        component: RegisterComponent,
      },
      {
        path: 'management',
        canActivate: [AdminGuard],
        component: ReservationManagementComponent,
      }
    ]
  },
  {
    path: 'activation',
    component: ActivationParentComponent,
    children: [
      {
        path: 'activation-successful',
        component: AccountActivationSuccessfulComponent,
      },
      {
        path: 'activation-failed',
        component: AccountActivationFailedComponent,
      },
      {
        path: 'already-activated',
        component: AccountAlreadyActivatedComponent,
      }
    ]
  },
  {
    path: '**',
    redirectTo: '/library',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RouteModule {
}

export const routedComponents = [LoginComponent, RegisterComponent, LibraryComponent];
