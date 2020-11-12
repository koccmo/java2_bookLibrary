package lesson_3_request_response_input_data_validation.code.after.core.services;

import java.util.ArrayList;
import java.util.List;

import lesson_3_request_response_input_data_validation.code.after.core.requests.AddBookRequest;
import lesson_3_request_response_input_data_validation.code.after.core.responses.CoreError;

public class AddBookRequestValidator {

	public List<CoreError> validate(AddBookRequest request) {
		List<CoreError> errors = new ArrayList<>();
		if (request.getBookTitle() == null || request.getBookTitle().isEmpty()) {
			CoreError error = new CoreError("bookTitle", "Must be not empty!");
			errors.add(error);
		}
		if (request.getBookAuthor() == null || request.getBookAuthor().isEmpty()) {
			CoreError error = new CoreError("bookAuthor", "Must be not empty!");
			errors.add(error);
		}
		return errors;
	}

}
