package internet_store.core.service.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.client.client_items.AddClientEmailRequest;
import internet_store.core.response.client.client_items.AddClientEmailResponse;
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