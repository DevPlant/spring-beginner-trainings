package com.devplant.introduction.rest.book.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BookReservationModel {

	@NotNull
	private LocalDateTime pickupTime;
	@NotNull
	private Long bookId;
}
