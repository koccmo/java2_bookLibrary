package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.request.RegistrationRequest;
import lv.javaguru.app.core.domain.CodeError;

import java.util.ArrayList;
import java.util.List;

public class RegisterRequestValidator extends Validator {
	public RegisterRequestValidator () {
	}

	public List<CodeError> validate (RegistrationRequest request) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(request.getUser().getName(), "first name", errorList);
		verifyNameAndSurname(request.getUser().getSurname(), "second name", errorList);

		return errorList;
	}





}
