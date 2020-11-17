package lesson_3.core.service.add_product;

import lesson_3.core.core_error.CoreError;
import lesson_3.core.domain.Product;

import java.util.List;

public interface ProductUpdate {
    void execute(List<CoreError> errors, Product product);
}
