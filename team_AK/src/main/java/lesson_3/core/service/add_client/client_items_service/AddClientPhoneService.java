package lesson_3.core.service.add_client.client_items_service;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.request.add_client.client_items.AddClientPhoneRequest;
import lesson_3.core.response.add_client.client_items.AddClientPhoneResponse;
import lesson_3.core.validate.PhoneNumberValidator;

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