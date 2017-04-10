import {Component} from "@angular/core";
import {RegistrationModel} from "./model/registration-model";
import {RegisterService} from "./service/register.service";

@Component({
  selector: 'app-register',
  providers: [RegisterService],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registrationModel: RegistrationModel;
  registrationSuccessful: boolean;
  registrationError: boolean;

  constructor(private registerService: RegisterService) {
    this.registrationModel = new RegistrationModel()
    this.registrationError = false;
    this.registrationSuccessful = false;
  }

  registerUser() {
    this.registerService.registerUser(this.registrationModel, () => {
      this.registrationSuccessful = true;
    }, () => {
      this.registrationError = true;
    });
  }


}
