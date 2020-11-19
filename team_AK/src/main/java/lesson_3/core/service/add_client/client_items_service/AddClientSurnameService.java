package lesson_3.core.service.add_client.client_items_service;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.request.add_client.client_items.AddClientSurnameRequest;
import lesson_3.core.response.add_client.client_items.AddClientSurnameResponse;
import lesson_3.core.validate.StringTypeValidator;

import java.util.List;

public class AddClientSurnameService {
    public AddClientSurnameResponse execute(AddClientSurnameRequest addClientSurnameRequest) {
        StringTypeValidator stringTypeValidator = new StringTypeValidator();
        List<CoreError> errors = stringTypeValidator.validate(addClientSurnameRequest.getSurname());

        if (errors.isEmpty()) {
            return new AddClientSurnameResponse(addClientSurnameRequest.getSurname());
        }
        return new AddClientSurnameResponse(errors);
    }
}