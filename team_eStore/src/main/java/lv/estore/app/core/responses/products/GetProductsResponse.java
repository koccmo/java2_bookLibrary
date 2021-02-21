package lv.estore.app.core.responses.products;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class GetProductsResponse extends CoreResponse {

    private List<Product> products;

    public GetProductsResponse(List<CoreError> errors, List<Product> products) {
        super(errors);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean hasProducts(){
        return products != null && !products.isEmpty();
    }
}
