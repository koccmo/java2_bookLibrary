package lv.javaguru.app.core.domain;

import java.util.Objects;

public class User {
	private String name;
	private String surname;
	private boolean isRegistered;
	private Long id;
	private static Long nextId = 100L;

	private PersonType personType;

	public PersonType getPersonType () {
		return personType;
	}

	public void setPersonType (PersonType personType) {
		this.personType = personType;
	}

	public User () {
		this.id = nextId++;
	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public User (String name, String surname) {
		this.id = nextId++;

		this.name = name;
		this.surname = surname;
	}

	public boolean isRegistered () {
		return isRegistered;
	}

	public void setRegistered (boolean registered) {
		isRegistered = registered;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(name, user.name) &&
				Objects.equals(surname, user.surname);
	}

	@Override
	public int hashCode () {
		return Objects.hash(name, surname);
	}

	@Override
	public String toString () {
		return id + " " + name + " " + surname;
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
