import {Component, OnInit} from "@angular/core";
import {BooksService} from "../library/service/books.service";
import {Book} from "../model/book-model";
import {Subject, Observable} from "rxjs";

@Component({
  selector: 'app-home',
  providers: [BooksService],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  books: Observable<Book[]>;

  private searchTerms = new Subject<string>();

  constructor(private booksService: BooksService) {

  }

  ngOnInit() {
    this.booksService.searchBooks('').subscribe((books: Book[]) => {
      this.books = Observable.of<Book[]>(books);
    });
    this.searchTerms
      .debounceTime(300)
      .distinctUntilChanged()
      .switchMap(term => this.booksService.searchBooks(term)).subscribe((books: Book[]) => {
      this.books = Observable.of<Book[]>(books);
    });
  }

  search(term: string): void {
    // Push a search term into the observable stream.
    this.searchTerms.next(term);
  }

}
