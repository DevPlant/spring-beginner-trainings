import {AuthorityModel} from "./authority-model";

export class UserModel {

  username: string;
  fullName: string;
  firstName: string;
  lastName: string;
  authorities: AuthorityModel[];

}
