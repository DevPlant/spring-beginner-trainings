import {Injectable} from "@angular/core";
import {Http, Response, URLSearchParams, RequestOptions} from "@angular/http";
import {environment} from "../../../environments/environment";
import {Book} from "../../model/book-model";
import {BookStock} from "../../model/book-stock-model";
import {ReservationModel} from "../../model/reservation-model";
import {Observable} from "rxjs";

@Injectable()
export class BooksService {

  constructor(private http: Http) {
  }

  findBook(bookId: number, bookLoaded: (book: Book) => any) {
    let params: URLSearchParams = new URLSearchParams();
    params.set('projection', 'bookProjection');
    this.http
      .get(environment.apiEndpoint + "books/" + bookId, {search: params})
      .map((res: Response) => res.json())
      .subscribe(bookLoaded,
        error =>
          console.log("An error occurred when requesting books.", error));
  }

  getAvailableStocks(bookId: string, stocksLoaded: (bookStocks: BookStock[]) => any) {
    let params: URLSearchParams = new URLSearchParams();
    params.set('bookId', bookId);
    params.set('projection', 'bookStockProjection');

    this.http
      .get(environment.apiEndpoint + "bookStocks/search/findByAvailableStocksForBook", {search: params})
      .map((res: Response) => res.json()._embedded.bookStocks)
      .subscribe(stocksLoaded,
        error =>
          console.log("An error occurred when requesting bookStocks/search.", error));
  }

  findMyStocks(bookId: string, username: string, stocksLoaded: (bookStock: BookStock) => any) {
    let params: URLSearchParams = new URLSearchParams();
    params.set('bookId', bookId);
    params.set('username', username);
    params.set('projection', 'bookStockProjection');

    this.http
      .get(environment.apiEndpoint + "bookStocks/search/findOneByUserIdAndBookId", {search: params})
      .map((res: Response) => res.json())
      .subscribe(stocksLoaded,
        error =>
          console.log("An error occurred when requesting bookStocks/search.", error));
  }

  saveReservation(reservationModel: ReservationModel, reservationCreated: (bookStock: BookStock) => any) {
    let options = new RequestOptions({withCredentials: true});
    this.http
      .post(environment.apiEndpoint + "books/reservation", reservationModel, options)
      .map((res: Response) => res.json())
      .subscribe(reservationCreated,
        error =>
          console.log("An error occurred when requesting books/reservation.", error));
  }

  cancelReservation(bookStockId: number, reservationDeleted: () => any) {
    let options = new RequestOptions({withCredentials: true});
    this.http
      .delete(environment.apiEndpoint + "books/reservation/" + bookStockId, options)
      .subscribe(reservationDeleted,
        error =>
          console.log("An error occurred when requesting books/reservation.", error));
  }

  searchBooks(term: string): Observable<Book[]> {
    let params: URLSearchParams = new URLSearchParams();
    params.set('term', term);
    return this.http
      .get(environment.apiEndpoint + 'books/search', {search: params})
      .map(response => response.json() as Book[])
      .catch((error: any) => {
        console.log("An error occurred when requesting books/search.", error);
        return Observable.throw(error.message || error);
      });
  }


  getAllBookStocks(bookStocksLoaded: (bookStock: BookStock[]) => any) {

    let params: URLSearchParams = new URLSearchParams();
    params.set('projection', 'bookStockWithBookProjection');

    this.http
      .get(environment.apiEndpoint + 'bookStocks/search/findReservedStocks')
      .map(response => response.json()._embedded.bookStocks as BookStock[])
      .subscribe(bookStocksLoaded,
        error =>
          console.log("An error occurred when requesting books/reservation.", error));
  }

  markAsReturned(bookStockId: number, markAsReturned: () => any) {
    let options = new RequestOptions({withCredentials: true});

    this.http
      .post(environment.apiEndpoint + 'bookStocks/returned/' + bookStockId, {},options)
      .subscribe(markAsReturned,
        error =>
          console.log("An error occurred when requesting books/reservation.", error));
  }

  markAsPickedUp(bookStockId: number, markAsPickedUp: () => any) {
    let options = new RequestOptions({withCredentials: true});

    this.http
      .post(environment.apiEndpoint + 'bookStocks/picked-up/' + bookStockId, {},options)
      .subscribe(markAsPickedUp,
        error =>
          console.log("An error occurred when requesting books/reservation.", error));
  }
}
