package lesson_3.core.validate;

import lesson_3.core.core_error.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ProductQuantityValidator {
    public List<CoreError> validate(int productQuantity, int userInputQuantity) {
        List<CoreError> errors = new ArrayList<>();
        if (productQuantity == 0) {
            errors.add(new CoreError("Add to cart error ", "Product quantity is zero"));
        }
        if ((productQuantity - userInputQuantity) < 0) {
            errors.add(new CoreError("Quantity error ", "No more product's quantity"));
        }
        return errors;
    }
}