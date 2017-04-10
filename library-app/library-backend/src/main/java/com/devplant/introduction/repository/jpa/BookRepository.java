package com.devplant.introduction.repository.jpa;

import com.devplant.introduction.domain.Book;
import com.devplant.introduction.domain.projection.BookProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = BookProjection.class)
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAuthorName(@Param("authorName") String authorName);

	@Query("SELECT b FROM Book b JOIN b.publishers p WHERE p.name = ?1")
	List<Book> findByPublisherName(@Param("publisherName") String publisherName);

	@Query("SELECT DISTINCT b FROM Book b JOIN b.stocks s WHERE s.user IS NULL")
	List<Book> findByAvailableStocks();


}
