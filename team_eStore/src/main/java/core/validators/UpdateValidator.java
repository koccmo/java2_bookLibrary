package core.validators;

import core.request.CoreRequest;
import core.responses.CoreError;
import core.responses.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UpdateValidator implements iValidator{

    @Override
    public Errors validate(final CoreRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.add(validateForEmptyField("Name", request.getName()).orElse(null));
        errors.add(validateForEmptyField("Description", request.getDescription()).orElse(null));
        errors.add(validateForEmptyField("Price", request.getPrice()).orElse(null));
        errors.add(validatePrice(request.getPrice()).orElse(null));
        return new Errors(errors);
    }

    private Optional<CoreError> validateForEmptyField(final String fieldName, final String value){
        return value == null || value.length() == 0
                ? Optional.of(new CoreError(fieldName,  "Field should not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePrice(final String price) {
        String regex = "^(0|\\+?[1-9]\\d*)$[.][0-9]{2}";

        if (!price.matches(regex)) {
            return Optional.of(new CoreError("Price", "Field should be valid"));
        } else {
            return Optional.empty();
        }
    }
}
