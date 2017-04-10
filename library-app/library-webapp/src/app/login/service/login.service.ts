import {Injectable} from "@angular/core";
import {LoginModel} from "../model/login-model";
import {Response, Http, Headers} from "@angular/http";
import {environment} from "../../../environments/environment";
import {UserModel} from "../../model/user-model";
import {UserService} from "../../user/user.service";
import {Router} from "@angular/router";

@Injectable()
export class LoginService {

  constructor(private http: Http, private userService: UserService, private router: Router) {
  }

  doLogin(loginModel: LoginModel, loginSuccessful: () => void, loginError: () => void) {
    let headers = new Headers({
      'Authorization': 'Basic ' + btoa(loginModel.username + ":" + loginModel.password)
    });
    this.http
      .get(environment.apiEndpoint + "/user-management/self", {headers: headers})
      .map((res: Response) => res.json())
      .subscribe((user: UserModel) => {
          this.userService.setCurrentUser(user);
          this.router.navigateByUrl("/library");
          loginSuccessful();
        },
        error => {
          console.log("An error occurred when requesting user-management/self.", error);
          loginError();
        });
  }

}
