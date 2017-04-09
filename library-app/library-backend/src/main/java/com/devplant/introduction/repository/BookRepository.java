package com.devplant.introduction.repository;

import com.devplant.introduction.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAuthorName(@Param("authorName") String authorName);

	@Query("SELECT b FROM Book b JOIN b.publishers p WHERE p.name = ?1")
	List<Book> findByPublisherName(@Param("publisherName") String publisherName);

	@Query("SELECT DISTINCT b FROM Book b JOIN b.stocks s WHERE s.user IS NULL")
	List<Book> findByAvailableStocks();


}
