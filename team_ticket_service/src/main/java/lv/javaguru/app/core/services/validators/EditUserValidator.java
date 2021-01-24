package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.request.UserEditRequest;
import lv.javaguru.app.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@DIComponent
public class EditUserValidator extends Validator {

	public List<CodeError> validate (UserEditRequest request) {
		List<CodeError> errors = new ArrayList<>();
		validateId(request).ifPresent(errors::add);

		return errors;
	}

	public List<CodeError> validate (UserEditRequest.Name request) {
		return validateName(request.getName());
	}

	public List<CodeError> validate (UserEditRequest.Surname request) {
		return validateSurname(request.getSurname());
	}

	private Optional<CodeError> validateId (UserEditRequest request) {
		return (request.getId() == null)
				? Optional.of(new CodeError("UserId", "Must not be empty!"))
				: Optional.empty();
	}


	private List<CodeError> validateName (String name) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(name, "User name", errorList);

		return errorList;
	}

	private List<CodeError> validateSurname (String surname) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(surname, "User surname", errorList);

		return errorList;
	}

}
