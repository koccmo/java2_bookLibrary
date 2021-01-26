package internet_store.application.core.responses.product;

import internet_store.application.core.domain.Product;
import internet_store.application.core.responses.CoreResponse;

import java.util.List;

public class GetAllProductsResponse extends CoreResponse {

    private List<Product> productList;

    public GetAllProductsResponse(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
