package com.devplant.introduction.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	private boolean pickedUp;


	@ManyToOne
	@JsonBackReference
	private Book book;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "user_id", updatable = false, insertable = false)
	private Long userId;

	public BookStock(Book book) {
		this.book = book;
	}
}
