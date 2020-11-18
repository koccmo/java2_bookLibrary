package lesson_3.core.response.add_client;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.core_error.CoreErrorResponse;

import java.util.List;

public class AddClientResponse extends CoreErrorResponse {
    public AddClientResponse(List<CoreError> errors) {
        super(errors);
    }
}