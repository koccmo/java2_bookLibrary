package lv.javaguru.app.core.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "person_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private PersonType personType;

	public User () {
	}

	public User (String name, String surname) {
		this.name = name.trim();
		this.surname = surname.trim();
		this.personType = PersonType.CLIENT;
	}

	public User (String name, String surname, PersonType personType) {
		this(name, surname);
		this.personType = personType;
	}


	public PersonType getPersonType () {
		return personType;
	}

	public void setPersonType (PersonType personType) {
		this.personType = personType;
	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}


	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id.equals(user.id) && name.equals(user.name) && surname.equals(user.surname);
	}

	@Override
	public int hashCode () {
		return Objects.hash(id, name, surname);
	}

	@Override
	public String toString () {
		return "ID: " + id +
				", " + name +
				", " + surname;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getSurname () {
		return surname;
	}

	public void setSurname (String surname) {
		this.surname = surname;
	}
}
