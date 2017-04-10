package com.devplant.introduction.domain.projection;

import com.devplant.introduction.domain.Author;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "authorProject", types = Author.class)
public interface AuthorProjection {

	String getName();
}
