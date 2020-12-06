package internet_store.lesson_5.core.responses;

import internet_store.lesson_5.core.domain.Product;

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
