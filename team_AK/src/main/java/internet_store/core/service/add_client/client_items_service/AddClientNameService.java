package internet_store.core.service.add_client.client_items_service;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.add_client.client_items.AddClientNameRequest;
import internet_store.core.response.add_client.client_items.AddClientNameResponse;
import internet_store.core.validate.StringTypeValidator;

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