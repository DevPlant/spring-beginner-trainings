import {Component} from "@angular/core";
import {LoginService} from "./service/login.service";
import {LoginModel} from "./model/login-model";

@Component({
  selector: 'app-login',
  providers: [LoginService],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginError: boolean;
  loginModel: LoginModel;

  constructor(private loginService: LoginService) {
    this.loginModel = new LoginModel();
    this.loginError = false;
  }

  doLogin() {
    this.loginService.doLogin(this.loginModel, () => {
      console.log('Login Successful');
    }, () => {
      this.loginError = true;
    });
  }

}
