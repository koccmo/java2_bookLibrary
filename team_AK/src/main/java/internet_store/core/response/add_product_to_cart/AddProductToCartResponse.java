package internet_store.core.response.add_product_to_cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class AddProductToCartResponse extends CoreErrorResponse {
    @Getter
    private long id;

    public AddProductToCartResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddProductToCartResponse(long id) {
        this.id = id;
    }
}