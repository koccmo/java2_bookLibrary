package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.domain.CodeError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddUserRequestValidator extends Validator {

	public List<CodeError> validate (UserAddRequest request) {
		List<CodeError> errorList = new ArrayList<>();

		verifyNameAndSurname(request.getName(), "First name", errorList);
		verifyNameAndSurname(request.getSurname(), "Second name", errorList);

		return errorList;
	}


}
