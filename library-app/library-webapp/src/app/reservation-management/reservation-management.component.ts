import { Component, OnInit } from '@angular/core';
import {UserService} from "../user/user.service";
import {BooksService} from "../library/service/books.service";
import {BookStock} from "../model/book-stock-model";

@Component({
  selector: 'app-reservation-management',
  providers: [UserService,BooksService],
  templateUrl: './reservation-management.component.html',
  styleUrls: ['./reservation-management.component.css']
})
export class ReservationManagementComponent implements OnInit {

  private bookStocks : BookStock[];

  constructor(private booksService: BooksService) {
    this.bookStocks = [];
  }

  ngOnInit() {
    this.booksService.getAllBookStocks((bookStocks: BookStock[]) =>{
      this.bookStocks = bookStocks;
    });
  }

  markAsPickedUp(bookStock: BookStock){
    this.booksService.markAsPickedUp(bookStock.id,() =>{

      this.booksService.getAllBookStocks((bookStocks: BookStock[]) =>{
        this.bookStocks = bookStocks;
      });

    });
  }

  markAsReturned(bookStock: BookStock){
    this.booksService.markAsReturned(bookStock.id, () =>{

      this.booksService.getAllBookStocks((bookStocks: BookStock[]) =>{
        this.bookStocks = bookStocks;
      });

    });
  }

}
