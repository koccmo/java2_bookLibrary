package bookLibrary.core.service.validators;

import bookLibrary.core.request.GetBookIdRequest;
import bookLibrary.core.response.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetBookIdValidator {
    @Autowired
    private GetBookIdFieldValidator fieldValidator;
    @Autowired
    private GetBookIdHasBookValidator hasBookValidator;

    public List<CoreError> validate(GetBookIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(fieldValidator.validate(request));
        validateHasBookInDataBase(request, errors);
        return errors;
    }

    private void validateHasBookInDataBase(GetBookIdRequest request, List<CoreError> errors) {
        if(errors.isEmpty()) {
            errors.addAll(hasBookValidator.validate(request));
        }
    }
}
