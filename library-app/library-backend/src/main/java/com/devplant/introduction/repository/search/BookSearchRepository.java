package com.devplant.introduction.repository.search;

import com.devplant.introduction.domain.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface BookSearchRepository extends ElasticsearchRepository<Book, Long> {
}
