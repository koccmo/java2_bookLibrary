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

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "role_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private PersonType personType;


	public User (String name, String lastName, PersonType personType) {
		this.name = name.trim();
		this.lastName = lastName.trim();
		this.personType = personType;
	}


	@Override
	public String toString () {
		return "ID: " + id +
				", " + name +
				", " + lastName;
	}


}
