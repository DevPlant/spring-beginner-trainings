package com.devplant.introduction.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "book", "user" })
@NoArgsConstructor
public class BookStock {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private LocalDateTime reservationDate;

	private LocalDateTime pickupDate;

	private LocalDateTime returnDate;

	@ManyToOne
	private Book book;

	@ManyToOne
	private User user;

	public BookStock(Book book) {
		this.book = book;
	}
}
