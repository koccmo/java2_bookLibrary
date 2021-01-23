package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.domain.CodeError;

import java.util.ArrayList;
import java.util.List;

public class EditFlightRequestValidator extends Validator {
	public EditFlightRequestValidator () {

	}

	public List<CodeError> validate (Long id) {
		List<CodeError> errorList = new ArrayList<>();

		if (id == null)
			errorList.add(new CodeError("Id", "Null"));
		return errorList;
	}

	public List<CodeError> validate (String name) {
		List<CodeError> errorList = new ArrayList<>();

		verifyCityAndCountry(name, "User name", errorList);

		return errorList;
	}


}
