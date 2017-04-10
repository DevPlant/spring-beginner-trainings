import {Author} from "./author-model";
import {Publisher} from "./publisher-model";
import {BookStock} from "./book-stock-model";

export class Book {
  id: number;
  year: number;
  name: string;
  synopsis: string;
  isbn: string;
  author: Author;
  publisher: Publisher[];
  bookStocks: BookStock[];

  constructor() {
    this.author = new Author();
    this.bookStocks = [];
    this.publisher = [];
  }
}
