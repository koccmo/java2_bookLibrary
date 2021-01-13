package lv.javaguru.app.core.response.edit;

import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.Response;

import java.util.List;

public class EditTicketDestinationResponse extends Response {

	public EditTicketDestinationResponse (List<CodeError> errorList) {
		super(errorList);
	}

}
