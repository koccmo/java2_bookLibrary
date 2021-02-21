package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.request.UserEditRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EditUserRequestValidator extends Validator {

	public List<CodeError> validate (UserEditRequest request) {
		List<CodeError> errors = new ArrayList<>();

		validateId(request).ifPresent(errors::add);

		return errors;

	}

	private Optional<CodeError> validateId (UserEditRequest request) {
		return (request.getId() == null)
				? Optional.of(new CodeError("UserId", "Must not be empty!"))
				: Optional.empty();
	}


	public List<CodeError> validateName (String name) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(name, "User name", errorList);

		return errorList;
	}

	public List<CodeError> validateSurname (String surname) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(surname, "User surname", errorList);

		return errorList;
	}

}
