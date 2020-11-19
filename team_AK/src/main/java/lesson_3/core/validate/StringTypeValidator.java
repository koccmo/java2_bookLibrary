package lesson_3.core.validate;

import lesson_3.core.core_error.CoreError;

import java.util.ArrayList;
import java.util.List;

public class StringTypeValidator {
    List<CoreError> errors = new ArrayList<>();

    public List<CoreError> validate(String stringRequest) {
        if (stringRequest == null) {
            errors.add(new CoreError("Input error", "Empty field"));
            return errors;
        }
        if (stringRequest.isEmpty()) {
            errors.add(new CoreError("Input error", "Empty field"));
        }
        return errors;
    }
}