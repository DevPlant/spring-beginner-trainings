package com.devplant.introduction.repository;

import com.devplant.introduction.domain.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookSearchRepository extends ElasticsearchRepository<Book, Long> {
}
