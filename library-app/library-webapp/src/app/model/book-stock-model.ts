import {UserModel} from "./user-model";

export class BookStock{

  id: number;
  reservationDate: Date;
  pickupDate: Date;
  returnDate: Date;
  userId: number;
  user: UserModel;
  pickedUp: boolean;
  bookName: string;

}
