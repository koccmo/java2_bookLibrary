package lesson_3_request_response_input_data_validation.code.after.core.responses;

import java.util.List;

public class DeleteBookResponse extends CoreResponse {

	public DeleteBookResponse() { }

	public DeleteBookResponse(List<CoreError> errors) {
		super(errors);
	}
}
