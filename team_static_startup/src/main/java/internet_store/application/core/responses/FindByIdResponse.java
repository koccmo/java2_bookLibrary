package internet_store.application.core.responses;

import internet_store.application.core.domain.Product;

import java.util.List;
import java.util.Optional;

public class FindByIdResponse {

    List<Product> productFoundById;

    public FindByIdResponse(List productFoundById) {
        this.productFoundById = productFoundById;
    }

    public List getProductFoundById() {
        return productFoundById;
    }
}
