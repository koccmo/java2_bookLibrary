package lv.estore.app.core.responses.products;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class SearchProductResponse extends CoreResponse {

    private List<Product> products;

    public SearchProductResponse(final List<Product> products, final List<CoreError> errors) {
        super(errors);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
