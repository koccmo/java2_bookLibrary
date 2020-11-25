package internet_store.core.service.add_client.client_items_service;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.add_client.client_items.AddClientSurnameRequest;
import internet_store.core.response.add_client.client_items.AddClientSurnameResponse;
import internet_store.core.validate.StringTypeValidator;

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