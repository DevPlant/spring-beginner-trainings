package com.devplant.introduction.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "reservedBookStocks" })
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Email
	@Column(unique = true)
	private String email;

	@NotEmpty
	@Column(unique = true)
	private String username;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	private String password;

	@Column(unique = true)
	private String activationId;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<BookStock> reservedBookStocks;

	@OneToMany(cascade = CascadeType.ALL)
	private List<UserAuthority> authorities;

	private boolean accountNonExpired = true;

	private boolean accountNonLocked = true;

	private boolean credentialsNonExpired = true;

	private boolean enabled;

	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = email;
		this.email = email;
		this.password = password;
		this.activationId = UUID.randomUUID().toString();
	}

}
