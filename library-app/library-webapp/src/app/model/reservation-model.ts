export class ReservationModel{
  pickupTime: Date;
  bookId: number;

  constructor(){
    this.pickupTime = new Date();
  }
}
