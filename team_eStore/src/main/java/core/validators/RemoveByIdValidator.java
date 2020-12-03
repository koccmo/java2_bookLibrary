package core.validators;

import core.request.CoreRequest;
import core.responses.CoreError;
import core.responses.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveByIdValidator implements iValidator{

    @Override
    public Errors validate(final CoreRequest request) {
        Long id = request.getId();
        List<CoreError> errors = new ArrayList<>();
        errors.add(validateForEmptyField("Id", id).orElse(null));
        errors.add(validateId(id).orElse(null));
        return new Errors(errors);
    }

    private Optional<CoreError> validateForEmptyField(final String fieldName, final Long value){
        return value == null
                ? Optional.of(new CoreError(fieldName,  "Field should not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateId(final Long id){
        return id <= 0
                ? Optional.of(new CoreError("Id",  "Value should be valid"))
                : Optional.empty();
    }
}
