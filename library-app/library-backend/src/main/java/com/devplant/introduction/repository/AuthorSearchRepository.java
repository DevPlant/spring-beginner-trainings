package com.devplant.introduction.repository;

import com.devplant.introduction.domain.Author;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AuthorSearchRepository extends ElasticsearchRepository<Author, Long> {
}
