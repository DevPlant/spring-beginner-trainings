package com.devplant.introduction.domain.projection;

import com.devplant.introduction.domain.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userProjection", types = User.class)
public interface UserProjection {

	long getId();

	String getFullName();
}
