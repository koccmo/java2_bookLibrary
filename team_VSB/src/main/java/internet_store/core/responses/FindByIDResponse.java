package internet_store.core.responses;

import internet_store.core.domain.Product;

import java.util.List;
import java.util.Optional;

public class FindByIDResponse extends CoreResponse {

    private Optional<Product> productFindById;

    public FindByIDResponse(Optional<Product> ProductFindById) { this.productFindById = productFindById; }

    public FindByIDResponse(List<CoreError> errors) { super(errors); }

    public Optional<Product> getProductFindById() { return productFindById; }
}
