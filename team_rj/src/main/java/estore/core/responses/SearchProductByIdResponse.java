package estore.core.responses;

import estore.core.validation.CoreError;
import estore.core.domain.Product;

import java.util.List;

public class SearchProductByIdResponse extends CoreResponse {

    private Product product;
    private int productsFound;

    public SearchProductByIdResponse(List<CoreError> errors) {
        super(errors);
        this.productsFound = -1;
    }

    public SearchProductByIdResponse(Product product, int productsFound) {
        this.product = product;
        this.productsFound = productsFound;
    }

    public Product getProduct() {
        return product;
    }

    public int getProductsFound() {
        return productsFound;
    }

}
