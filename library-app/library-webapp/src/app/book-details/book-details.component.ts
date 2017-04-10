import {Component, OnInit} from "@angular/core";
import {Book} from "../model/book-model";
import {BooksService} from "../library/service/books.service";
import {ActivatedRoute} from "@angular/router";
import {BookStock} from "../model/book-stock-model";
import {UserService} from "../user/user.service";
import {MdDialog} from "@angular/material";
import {ReservationModel} from "../model/reservation-model";

@Component({
  selector: 'app-book-details',
  providers: [BooksService, UserService],
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  private book: Book;
  private rangeStart: Date;
  private alreadyReserved: boolean;
  private rangeEnd: Date;
  private reservationModel: ReservationModel;
  private showReservationCard: boolean;
  private reservationBookStock: BookStock;

  constructor(private booksService: BooksService, private route: ActivatedRoute, public userService: UserService, public dialog: MdDialog) {
    this.book = new Book();
    this.reservationModel = new ReservationModel();
    this.showReservationCard = false;
    this.rangeStart = new Date();
    this.rangeEnd = new Date();
    this.alreadyReserved = false;
    this.rangeEnd.setDate(this.rangeEnd.getDate() + 3);
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.reservationModel.bookId = +params['bookId'];
      this.booksService.findBook(+params['bookId'], (book: Book) => {

        book.bookStocks = [];
        this.book = book;

        this.booksService.getAvailableStocks(params['bookId'], (bookStocks: BookStock[]) => {
          this.book.bookStocks = bookStocks;
        });

        if (this.userService.isAuthenticated()) {

          this.booksService.findMyStocks(params['bookId'], this.userService.getCurrentUser().username, (bookStock: BookStock) => {
            this.alreadyReserved = true;
            this.reservationBookStock = bookStock;
          });

        }

      });
    });
  }

  makeReservation() {
    this.showReservationCard = true;
  }


  saveReservation() {
    this.booksService.saveReservation(this.reservationModel, (bookStock: BookStock) => {
      this.alreadyReserved = true;
      this.showReservationCard = false;
      this.reservationBookStock = bookStock;
    });
  }

  hideReservation() {
    this.showReservationCard = false;
  }

  cancelReservation() {
    this.booksService.cancelReservation(this.reservationBookStock.id, () => {
      this.alreadyReserved = false;
      this.reservationBookStock = null;

      this.booksService.getAvailableStocks(this.book.id.toString(), (bookStocks: BookStock[]) => {
        this.book.bookStocks = bookStocks;
      });

    });
  }

}


