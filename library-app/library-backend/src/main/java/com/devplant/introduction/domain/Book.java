package com.devplant.introduction.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@Document(indexName = "book")
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "publishers", "stocks", "author" })
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int year;
	private String name;

	@Column(length = 4000)
	private String synopsis;

	private String isbn;

	@ManyToMany(cascade = CascadeType.ALL)
	public List<Publisher> publishers;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private List<BookStock> stocks;

	@ManyToOne(cascade = CascadeType.ALL)
	private Author author;
}
