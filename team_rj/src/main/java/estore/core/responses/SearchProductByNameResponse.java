package estore.core.responses;

import estore.domain.Product;

import java.util.List;

public class SearchProductByNameResponse extends CoreResponse {

    private List<Product> products;

    /*public SearchProductByNameResponse(List<CoreError> errors) {
        super(errors);
    }*/

    public SearchProductByNameResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

}
