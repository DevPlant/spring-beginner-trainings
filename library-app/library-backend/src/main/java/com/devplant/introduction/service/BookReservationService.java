package com.devplant.introduction.service;

import com.devplant.introduction.domain.BookStock;
import com.devplant.introduction.domain.User;
import com.devplant.introduction.exception.BookAlreadyReservedByUserException;
import com.devplant.introduction.exception.BookNotAvailableForReservationException;
import com.devplant.introduction.exception.BookPickupDateIsToFarInTheFutureException;
import com.devplant.introduction.repository.jpa.BookStockRepository;
import com.devplant.introduction.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BookReservationService {

	@Autowired
	private BookStockRepository bookStockRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public BookStock reserveBook(LocalDateTime pickupDate, long bookId, String username) {

		LocalDateTime now = LocalDateTime.now();
		if (pickupDate.minusDays(3).isAfter(now)) {
			throw new BookPickupDateIsToFarInTheFutureException();
		}

		User user = userRepository.findOneByUsername(username);

		// If the user reserved this book already - throw an exception - he cannot get the same book twice ;)
		user.getReservedBookStocks().forEach(bookStock -> {
			if (bookStock.getBook().getId() == bookId) {
				throw new BookAlreadyReservedByUserException();
			}
		});

		// If a stock is available - reserve the first one

		BookStock bookStock = bookStockRepository.findByAvailableStocksForBook(bookId).stream().findFirst()
				.orElseThrow(BookNotAvailableForReservationException::new);

		// reserved now
		bookStock.setReservationDate(now);
		// pickup defined be user
		bookStock.setPickupDate(pickupDate);
		// return date is set to 1 week after pickup
		bookStock.setReturnDate(pickupDate.plusDays(7));

		// update relation in both directions
		bookStock.setUser(user);
		user.getReservedBookStocks().add(bookStock);

		// save
		userRepository.save(user);

		bookStock = bookStockRepository.save(bookStock);

		return bookStock;
	}
}
