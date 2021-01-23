package lv.javaguru.app.core.domain;

import java.util.Objects;

public class User {

	protected Long id;
	//private final String login;
	//private String password;
	private String name;
	private String surname;
	private PersonType personType;

//private User (String login, String password) {
//	this.login = login;
//	this.password = password;
//	this.personType = PersonType.CLIENT;
//}

	//public User (String login, String password, String name, String surname) {
//	this(login, password);
//	this.name = name;
//	this.surname = surname;
//}
	public User () {
	}

	public User (String name) {
		this.name = name;
	}

	public User (String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.personType = PersonType.CLIENT;
	}

	//
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
		return Objects.equals(name, user.name) &&
				Objects.equals(surname, user.surname);
	}

	@Override
	public int hashCode () {
		return Objects.hash(name, surname);
	}

	@Override
	public String toString () {
		return name + ", " + surname;
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
