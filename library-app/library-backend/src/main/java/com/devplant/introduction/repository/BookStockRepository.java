package com.devplant.introduction.repository;

import com.devplant.introduction.domain.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookStockRepository extends JpaRepository<BookStock, Long> {


	@Query("SELECT DISTINCT b FROM BookStock b WHERE b.book.id = ?1 AND b.user IS NULL")
	List<BookStock> findByAvailableStocksForBook(long bookId);
}
