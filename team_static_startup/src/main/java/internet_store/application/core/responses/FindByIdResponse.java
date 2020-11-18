package internet_store.application.core.responses;

import internet_store.application.core.domain.Product;

import java.util.List;

public class FindByIdResponse extends CoreResponse {

    private List<Product> productFoundById;
    private List<CoreError> errors;

    public FindByIdResponse(List<Product> productFoundById, List<CoreError> errors) {
        this.productFoundById = productFoundById;
        this.errors = errors;
    }

    public List getProductFoundById() {
        return productFoundById;
    }
}
