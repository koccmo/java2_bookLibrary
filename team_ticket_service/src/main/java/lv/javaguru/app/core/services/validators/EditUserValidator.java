package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.domain.CodeError;

import java.util.ArrayList;
import java.util.List;

public class EditUserValidator extends Validator {

	public EditUserValidator () {
	}

	public List<CodeError> validateName (String name) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(name, "User name", errorList);

		return errorList;
	}


}
