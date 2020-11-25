package internet_store_1.core.response.product;

import internet_store_1.core.domain.Product;
import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindAnyByTitleResponse extends CoreResponse {

    private Optional<Product> product;

    public FindAnyByTitleResponse(Optional<Product> product) {
        this.product = product;
    }

    public FindAnyByTitleResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Product> getProduct() {
        return product;
    }
}
