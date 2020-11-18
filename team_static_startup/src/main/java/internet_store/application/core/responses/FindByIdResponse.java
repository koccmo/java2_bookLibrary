package internet_store.application.core.responses;

import internet_store.application.core.domain.Product;

import java.util.List;

public class FindByIdResponse {

    private List<Product> productFoundById;
    private List<CoreError> foundErrorList;

    public FindByIdResponse(List<Product> productFoundById, List<CoreError> foundErrorList) {
        this.productFoundById = productFoundById;
        this.foundErrorList = foundErrorList;
    }

    public List getProductFoundById() {
        return productFoundById;
    }
}
