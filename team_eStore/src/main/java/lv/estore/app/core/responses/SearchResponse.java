package lv.estore.app.core.responses;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;

import java.util.List;

public class SearchResponse extends CoreResponse{

    private List<Product> products;

    public SearchResponse(final List<Product> products, final List<CoreError> errors) {
        super(errors);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
