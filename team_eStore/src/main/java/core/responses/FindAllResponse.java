package core.responses;

import domain.Product;

import java.util.List;

public class FindAllResponse extends CoreResponse{
    private List<Product> products;

    public FindAllResponse(Errors errors) {
        super(errors);
    }

    public FindAllResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
