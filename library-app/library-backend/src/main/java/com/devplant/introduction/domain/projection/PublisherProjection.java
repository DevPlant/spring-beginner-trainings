package com.devplant.introduction.domain.projection;

import com.devplant.introduction.domain.Publisher;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "publisherProjection", types = Publisher.class)
public interface PublisherProjection {

	String getName();

}
