package com.devplant.introduction.repository.jpa;

import com.devplant.introduction.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	List<Publisher> findByName(@Param("name") String name);

}
