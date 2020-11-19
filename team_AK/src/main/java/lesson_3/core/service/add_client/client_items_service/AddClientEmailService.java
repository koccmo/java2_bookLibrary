package lesson_3.core.service.add_client.client_items_service;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.request.add_client.client_items.AddClientEmailRequest;
import lesson_3.core.response.add_client.client_items.AddClientEmailResponse;
import lesson_3.core.validate.ClientEmailValidator;

import java.util.List;

public class AddClientEmailService {
    public AddClientEmailResponse execute(AddClientEmailRequest addClientEmailRequest) {
        ClientEmailValidator emailValidator = new ClientEmailValidator();

        List<CoreError> errors = emailValidator.validate(addClientEmailRequest);

        if (errors.isEmpty()) {
            return new AddClientEmailResponse(addClientEmailRequest.getEmail());
        }
        return new AddClientEmailResponse(errors);
    }
}