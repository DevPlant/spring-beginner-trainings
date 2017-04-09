package com.devplant.introduction.repository;

import com.devplant.introduction.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	List<Publisher> findByName(String name);

}
