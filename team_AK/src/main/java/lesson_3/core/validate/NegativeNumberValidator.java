package lesson_3.core.validate;

import lesson_3.core.core_error.CoreError;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NegativeNumberValidator<T> {
    private final T inputValue;

    public NegativeNumberValidator(T inputValue) {
        this.inputValue = inputValue;
    }

    public List<CoreError> validate() {
        List<CoreError> errors = new ArrayList<>();
        if (inputValue instanceof Integer) {
            if ((int) inputValue < 0) {
                errors.add(new CoreError("Integer input error ", "only positive number allowed"));
                return errors;
            }
        }

        if (inputValue instanceof Long) {
            if ((long) inputValue < 0) {
                errors.add(new CoreError("Long input error ", "only positive number allowed"));
                return errors;
            }
        }

        if (inputValue instanceof BigDecimal) {
            if (new BigDecimal(String.valueOf(inputValue)).compareTo(new BigDecimal("0")) < 0) {
                errors.add(new CoreError("BigDecimal input error ", "only positive number allowed"));
                return errors;
            }
        }
        return errors;
    }
}