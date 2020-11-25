package internet_store.core.service.add_client.client_items_service;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.add_client.client_items.AddClientEmailRequest;
import internet_store.core.response.add_client.client_items.AddClientEmailResponse;
import internet_store.core.validate.ClientEmailValidator;

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