package com.devplant.introduction.rest.book;

import com.devplant.introduction.domain.BookStock;
import com.devplant.introduction.repository.jpa.BookStockRepository;
import com.devplant.introduction.service.BookReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookStocks")
public class BookStockController {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private BookReservationService bookReservationService;

	@Autowired
	private BookStockRepository bookStockRepository;

	@RequestMapping(value = "/picked-up/{bookStockId}", method = RequestMethod.POST)
	public ResponseEntity<BookStock> bookPickedUp(@PathVariable("bookStockId") long bookStockId) {

		BookStock bookStock = bookStockRepository.findOne(bookStockId);
		bookStock.setPickedUp(true);
		bookStockRepository.save(bookStock);

		return new ResponseEntity<>(bookStock, HttpStatus.OK);
	}

	@RequestMapping(value = "/returned/{bookStockId}", method = RequestMethod.POST)
	public ResponseEntity<BookStock> bookReturned(@PathVariable("bookStockId") long bookStockId){

		BookStock bookStock = bookStockRepository.findOne(bookStockId);
		bookStock.setPickedUp(false);
		bookStock.setUser(null);
		bookStock.setReservationDate(null);
		bookStock.setPickupDate(null);
		bookStock.setReturnDate(null);
		bookStockRepository.save(bookStock);

		return new ResponseEntity<>(bookStock, HttpStatus.OK);
	}


}
