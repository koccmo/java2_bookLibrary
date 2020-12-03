package core.validators;

import core.request.CoreRequest;
import core.responses.CoreError;
import core.responses.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveByNameValidator implements iValidator{

    @Override
    public Errors validate(final CoreRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.add(validateForEmptyField("Name", request.getName()).orElse(null));
        return new Errors(errors);
    }

    private Optional<CoreError> validateForEmptyField(final String fieldName, final String value){
        return value == null || value.length() == 0
                ? Optional.of(new CoreError(fieldName,  "Field should not be empty"))
                : Optional.empty();
    }
}
