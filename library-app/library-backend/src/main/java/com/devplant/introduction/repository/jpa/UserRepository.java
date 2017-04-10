package com.devplant.introduction.repository.jpa;

import com.devplant.introduction.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByUsername(@Param("username") String username);

	User findOneByActivationId(@Param("activationId") String activationId);

}
