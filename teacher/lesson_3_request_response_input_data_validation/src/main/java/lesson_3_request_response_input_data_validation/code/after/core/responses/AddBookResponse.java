package lesson_3_request_response_input_data_validation.code.after.core.responses;

import java.util.List;

public class AddBookResponse extends CoreResponse {

	public AddBookResponse() { }

	public AddBookResponse(List<CoreError> errors) {
		super(errors);
	}
}
