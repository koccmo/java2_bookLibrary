package core.responses;

import domain.Product;

import java.util.List;

public class SearchResponse extends CoreResponse{
    private List<Product> products;

    public SearchResponse(final Errors errors) {
        super(errors);
    }

    public SearchResponse(final List<Product> products) {
        this.products = products;
    }

    public List<Product> getProduct() {
        return products;
    }
}
