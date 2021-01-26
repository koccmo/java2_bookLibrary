package estore.core.responses;

import estore.core.model.Product;

import java.util.List;

public class GetAllProductsResponse extends CoreResponse {

    private List<Product> products;

    public GetAllProductsResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
