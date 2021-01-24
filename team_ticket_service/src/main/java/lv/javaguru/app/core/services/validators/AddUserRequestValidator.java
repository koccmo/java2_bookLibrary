package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
@DIComponent
public class AddUserRequestValidator extends Validator {

	public AddUserRequestValidator () {
	}

	public List<CodeError> validate (UserAddRequest request) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(request.getUser().getName(), "first name", errorList);
		verifyNameAndSurname(request.getUser().getSurname(), "second name", errorList);

		return errorList;
	}





}
