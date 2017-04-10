package com.devplant.introduction.rest.search;

import com.devplant.introduction.domain.Book;
import com.devplant.introduction.repository.search.BookSearchRepository;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookSearchController {

	@Autowired
	private BookSearchRepository bookSearchRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/search")
	public ResponseEntity<Iterable<Book>> searchBooks(
			@RequestParam(required = false, defaultValue = "",name = "term") String term) {

		QueryBuilder queryBuilder = StringUtils.isEmpty(term) ?
				QueryBuilders.queryStringQuery("*") :
				QueryBuilders.matchPhrasePrefixQuery("_all", term);

		Iterable<Book> books = bookSearchRepository.search(queryBuilder);

		List<Book> bookList = new ArrayList<>();
		books.forEach(bookList::add);

		return new ResponseEntity<>(bookList, HttpStatus.OK);
	}
}
