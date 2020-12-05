package core.validators;

import core.request.CoreRequest;
import core.responses.Errors;

public interface iValidator {
    Errors validate(CoreRequest request);
}
