package lv.javaguru.app.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	//@Column(name = "name")
	//private String name;
//
	//@Column(name = "last_name")
	//private String lastName;
//
	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	//@Column(name = "role_type", nullable = false)
	//@Enumerated(EnumType.STRING)
	//private PersonType personType;


	public User (String username, String password, boolean enabled) {
		this.username = username.trim();
		this.password = password.trim();
		this.enabled = enabled;
	}


	@Override
	public String toString () {
		return "ID: " + id +
				", " + username +
				", " + password;
	}


}
