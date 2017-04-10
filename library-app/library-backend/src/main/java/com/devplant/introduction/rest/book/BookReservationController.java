package com.devplant.introduction.rest.book;

import com.devplant.introduction.domain.BookStock;
import com.devplant.introduction.exception.BookAlreadyReservedByUserException;
import com.devplant.introduction.exception.BookNotAvailableForReservationException;
import com.devplant.introduction.exception.BookPickupDateIsToFarInTheFutureException;
import com.devplant.introduction.repository.jpa.BookStockRepository;
import com.devplant.introduction.rest.book.model.BookReservationModel;
import com.devplant.introduction.service.BookReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/books")
public class BookReservationController {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private BookReservationService bookReservationService;

	@Autowired
	private BookStockRepository bookStockRepository;


	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	public ResponseEntity<BookStock> makeReservation(@RequestBody BookReservationModel bookReservationModel,
			Principal principal) {

		BookStock bookStock = bookReservationService
				.reserveBook(bookReservationModel.getPickupTime(), bookReservationModel.getBookId(),
						principal.getName());

		return new ResponseEntity<>(bookStock, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/reservation/{bookStockId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> makeReservation(@PathVariable("bookStockId") long bookStockId, Principal principal) {

		BookStock bookStock = bookStockRepository.findOne(bookStockId);
		if (bookStock.getUser().getUsername().equals(principal.getName())) {
			bookStock.setUser(null);
			bookStock.setPickedUp(false);
			bookStock.setPickupDate(null);
			bookStock.setReservationDate(null);
			bookStockRepository.save(bookStock);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = { BookAlreadyReservedByUserException.class })
	protected String handleBookAlreadyReservedByUserException(BookAlreadyReservedByUserException exception)
			throws JsonProcessingException {
		return objectMapper.writeValueAsString(exception);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = { BookNotAvailableForReservationException.class })
	protected String handleBookNotAvailableForReservationException(BookNotAvailableForReservationException exception)
			throws JsonProcessingException {
		return objectMapper.writeValueAsString(exception);
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = { BookPickupDateIsToFarInTheFutureException.class })
	protected String handleBookPickupDateIsToFarInTheFutureException(
			BookPickupDateIsToFarInTheFutureException exception) throws JsonProcessingException {
		return objectMapper.writeValueAsString(exception);
	}
}
