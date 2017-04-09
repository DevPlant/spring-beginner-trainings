package com.devplant.introduction.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class UserAuthority implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String authority;

	@Override
	public String getAuthority() {
		return authority;
	}
}
