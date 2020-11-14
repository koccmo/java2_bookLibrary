package estore.core.responses;

import estore.domain.Product;

import java.util.List;

public class ShowAllProductsResponse {

    private List<Product> products;

    public ShowAllProductsResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
