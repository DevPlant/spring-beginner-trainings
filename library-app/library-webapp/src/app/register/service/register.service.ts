import {Injectable} from "@angular/core";
import {Http, Headers, Response} from "@angular/http";
import {environment} from "../../../environments/environment";
import {RegistrationModel} from "../model/registration-model";

@Injectable()
export class RegisterService {


  constructor(private http: Http) {
  }


  registerUser(registerModel: RegistrationModel, onResponse: (response: Response) => void, onError: (error: any) =>void): void {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });
    this.http
      .post(environment.apiEndpoint + "/user-management/register", JSON.stringify(registerModel), {headers: headers})
      .subscribe(onResponse,onError);
  }


}
