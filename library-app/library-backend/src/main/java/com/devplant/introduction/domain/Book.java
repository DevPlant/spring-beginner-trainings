package com.devplant.introduction.domain;

import com.devplant.introduction.service.BookSaveListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Document(indexName = "book-search")
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "publishers", "stocks", "author" })
@EntityListeners(BookSaveListener.class)
public class Book {

	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int year;
	private String name;

	@Column(length = 4000)
	private String synopsis;

	private String isbn;

	@ManyToMany(cascade = CascadeType.ALL)
	@Field(type = FieldType.Object, ignoreFields = { "books" })
	public List<Publisher> publishers;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	@Field(type = FieldType.Object, ignoreFields = { "book", "user" })
	private List<BookStock> stocks;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Field(type = FieldType.Object, ignoreFields = { "authoredBooks" })
	private Author author;
}
