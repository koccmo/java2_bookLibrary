package internet_store.application.core.responses.product;

import internet_store.application.core.domain.Product;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindByProductIdResponse extends CoreResponse {

    private Optional<Product> productFoundById;

    public FindByProductIdResponse(Optional<Product> productFoundById) {
        this.productFoundById = productFoundById;
    }

    public FindByProductIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Product> getProductFoundById() {
        return productFoundById;
    }

}
