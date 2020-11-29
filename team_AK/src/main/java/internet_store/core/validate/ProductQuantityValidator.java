package internet_store.core.validate;

import internet_store.core.core_error.CoreError;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductQuantityValidator {
    public List<CoreError> validate(BigDecimal productQuantity, BigDecimal userInputQuantity) {
        List<CoreError> errors = new ArrayList<>();

        if (productQuantity.compareTo(BigDecimal.ZERO) == 0) {
            errors.add(new CoreError("Quantity error ", "Product quantity is zero"));
        }
        if ((productQuantity.subtract(userInputQuantity)).compareTo(BigDecimal.ZERO) < 0) {
            errors.add(new CoreError("Quantity error ", "No more product's quantity"));
        }
        return errors;
    }
}