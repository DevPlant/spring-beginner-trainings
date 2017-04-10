package com.devplant.introduction.domain.projection;

import com.devplant.introduction.domain.Book;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "bookProjection", types = Book.class)
public interface BookProjection {

	long getId();

	String getName();

	String getYear();

	String getSynopsis();

	String getIsbn();

	AuthorProjection getAuthor();

	List<PublisherProjection> getPublishers();

}
