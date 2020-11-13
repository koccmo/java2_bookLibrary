package estore.responses;

import estore.domain.Product;

import java.util.List;

public class SearchProductByNameResponse {

    private List<Product> products;

    public SearchProductByNameResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

}
