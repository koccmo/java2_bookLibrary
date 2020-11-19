package internet_store.application.core.responses;

import internet_store.application.core.domain.Product;

import java.util.List;
import java.util.Optional;

public class FindByIdResponse extends CoreResponse {

    private Optional<Product> productFoundById;
    //private List<CoreError> errors;

    public FindByIdResponse(Optional<Product> productFoundById) {
        this.productFoundById = productFoundById;
    }

    public FindByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Product> getProductFoundById() {
        return productFoundById;
    }
}
