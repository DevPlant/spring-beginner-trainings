package com.devplant.introduction.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
@Document(indexName = "publisher")
@EqualsAndHashCode(of = "id")
@ToString(exclude = "books")
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(unique = true)
	private String name;

	@ManyToMany(mappedBy = "publishers")
	private List<Book> books;
}
