package internet_store.core.service.add_client.client_items_service;

import internet_store.core.core_error.CoreError;
import internet_store.core.request.add_client.client_items.AddClientPhoneRequest;
import internet_store.core.response.add_client.client_items.AddClientPhoneResponse;
import internet_store.core.validate.PhoneNumberValidator;

import java.util.List;

public class AddClientPhoneService {
    public AddClientPhoneResponse execute(AddClientPhoneRequest addClientPhoneRequest) {
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
        List<CoreError> errors = phoneNumberValidator.validate(addClientPhoneRequest,"LV");

        if (errors.isEmpty()) {
            return new AddClientPhoneResponse(addClientPhoneRequest.getPhoneNumber());
        }
        return new AddClientPhoneResponse(errors);
    }
}