package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.Person;

import java.util.List;

public class EditTicketResponse extends Response {

	private Long id;

	public EditTicketResponse (long id) {
		this.id = id;
	}

	public EditTicketResponse (long id, Person person) {
		this.id = id;
	}

	public EditTicketResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public Long getId () {
		return id;
	}
}
