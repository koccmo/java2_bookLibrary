package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.domain.CodeError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginRequestValidator extends Validator {

	public List<CodeError> validate (LogInRequest request) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(request.getName(), "User name", errorList);
		verifyNameAndSurname(request.getSurname(), "User surname", errorList);

		return errorList;
	}
}
