package estore.core.responses;

import estore.core.validation.CoreError;
import estore.core.domain.Product;

import java.util.List;

public class SearchProductByCategoryResponse extends CoreResponse {

    private List<Product> products;
    private int productsFound;

    public SearchProductByCategoryResponse(List<CoreError> errors) {
        super(errors);
        this.productsFound = -1;
    }

    public SearchProductByCategoryResponse(List<Product> products, int productsFound) {
        this.products = products;
        this.productsFound = productsFound;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getProductsFound() {
        return productsFound;
    }

}
