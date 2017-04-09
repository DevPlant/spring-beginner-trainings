package com.devplant.introduction.repository;

import com.devplant.introduction.domain.Publisher;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PublisherSearchRepository extends ElasticsearchRepository<Publisher, Long> {
}
