package lv.javaguru.app.core.request;


public class LogInRequest {

	private String name;
	private String surname;

	public LogInRequest () {
	}

	public LogInRequest (String name, String surname) {
		this.name = name;
		this.surname = surname;
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
