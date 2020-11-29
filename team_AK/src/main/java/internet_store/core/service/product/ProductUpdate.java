package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;

import java.util.List;

public interface ProductUpdate {
    void execute(List<CoreError> errors, Product product);
}
