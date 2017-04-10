package com.devplant.introduction.domain.projection;

import com.devplant.introduction.domain.BookStock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "bookStockProjection", types = BookStock.class)
public interface BookStockProjection {

	long getId();

	LocalDateTime getReservationDate();

	LocalDateTime getPickupDate();

	LocalDateTime getReturnDate();

	UserProjection getUser();

	boolean isPickedUp();

	@Value("#{target.book.name}")
	String getBookName();
}
