package com.devplant.introduction.repository.jpa;

import com.devplant.introduction.domain.BookStock;
import com.devplant.introduction.domain.projection.BookStockProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = BookStockProjection.class)
public interface BookStockRepository extends JpaRepository<BookStock, Long> {

	@Query("SELECT DISTINCT b FROM BookStock b WHERE b.book.id = ?1 AND b.user IS NULL")
	List<BookStock> findByAvailableStocksForBook(@Param("bookId") long bookId);

	@Query("SELECT DISTINCT b FROM BookStock b WHERE  b.user IS NOT NULL")
	List<BookStock> findReservedStocks();

	@Query("SELECT DISTINCT b FROM BookStock b WHERE b.book.id = ?2 AND b.user.username = ?1")
	BookStock findOneByUserIdAndBookId(@Param("username") String username, @Param("bookId") long bookId);

}
