package lesson_3.core.service.add_client.client_items_service;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.request.add_client.client_items.AddClientNameRequest;
import lesson_3.core.response.add_client.client_items.AddClientNameResponse;
import lesson_3.core.validate.StringTypeValidator;

import java.util.List;

public class AddClientNameService {
    public AddClientNameResponse execute(AddClientNameRequest addClientNameRequest) {
        StringTypeValidator stringTypeValidator = new StringTypeValidator();
        List<CoreError> errors = stringTypeValidator.validate(addClientNameRequest.getName());

        if (errors.isEmpty()) {
            return new AddClientNameResponse(addClientNameRequest.getName());
        }
        return new AddClientNameResponse(errors);
    }
}