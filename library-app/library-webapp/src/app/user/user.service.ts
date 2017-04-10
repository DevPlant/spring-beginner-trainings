import {Injectable} from "@angular/core";
import {UserModel} from "../model/user-model";
import {Router} from "@angular/router";
import {Http, Response, RequestOptions} from "@angular/http";
import {environment} from "../../environments/environment";

@Injectable()
export class UserService {

  userModel: UserModel;

  constructor(private router: Router, private http: Http) {


    this.initialize();
  }

  initialize() {
    let options = new RequestOptions({withCredentials: true});

    this.http
      .get(environment.apiEndpoint + "/user-management/self", options)
      .map((res: Response) => res.json())
      .subscribe((user: UserModel) => {
          console.log('Just got user: ', user);
          this.setCurrentUser(user);
        },
        error =>
          console.log("An error occurred when requesting api/foobar.", error));
  }

  setCurrentUser(userModel: UserModel) {
    this.userModel = userModel;
  }

  public getCurrentUser(): UserModel {
    return this.userModel;
  }

  isAuthenticated(): boolean {
    return this.userModel != null;
  }

  isAdmin(): boolean {
    if (this.userModel) {
      for (let entry of this.userModel.authorities) {
        if (entry.authority == 'ROLE_ADMIN') {
          return true;
        }
      }
    }
    return false;
  }

  logout() {
    let options = new RequestOptions({withCredentials: true});
    this.http
      .post(environment.apiEndpoint + "logout", {}, options)
      .subscribe(() => {
          this.userModel = null;
          this.router.navigateByUrl("/library");
        },
        error =>
          console.log("An error occurred when requesting api/foobar.", error));
  }
}
