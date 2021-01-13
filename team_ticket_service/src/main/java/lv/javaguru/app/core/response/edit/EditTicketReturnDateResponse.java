package lv.javaguru.app.core.response.edit;

import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.Response;

import java.util.List;

public class EditTicketReturnDateResponse extends Response {

	public EditTicketReturnDateResponse (List<CodeError> errorList) {
		super(errorList);
	}

}
