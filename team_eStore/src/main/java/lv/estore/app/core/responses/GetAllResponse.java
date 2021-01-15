package lv.estore.app.core.responses;

import lv.estore.app.core.domain.Product;

import java.util.List;

public class GetAllResponse extends CoreResponse{
    private List<Product> products;

    public GetAllResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
