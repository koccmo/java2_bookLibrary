package internet_store_1.core.response.product;

import internet_store_1.core.domain.Product;
import internet_store_1.core.response.CoreError;
import internet_store_1.core.response.CoreResponse;

import java.util.List;

public class FindAllByTitleResponse extends CoreResponse {

    List<Product> productList;

    public FindAllByTitleResponse(List<Product> productList) {
        this.productList = productList;
    }

    public FindAllByTitleResponse(List<CoreError> errors, List<Product> productList) {
        super(errors);
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
