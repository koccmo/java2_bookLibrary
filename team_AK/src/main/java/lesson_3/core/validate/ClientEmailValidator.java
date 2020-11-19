package lesson_3.core.validate;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.request.add_client.client_items.AddClientEmailRequest;

import java.util.ArrayList;
import java.util.List;

public class ClientEmailValidator {
    public List<CoreError> validate(AddClientEmailRequest addClientEmailRequest) {
        List<CoreError> errors = new ArrayList<>();

        boolean emailValidate = org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(addClientEmailRequest.getEmail());

        if (!(emailValidate)) {
            errors.add(new CoreError("Email input error", "email unsupported format"));
        }
        return errors;
    }
}