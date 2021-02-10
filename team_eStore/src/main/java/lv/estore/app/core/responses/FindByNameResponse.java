package lv.estore.app.core.responses;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;

import java.util.List;

public class FindByNameResponse extends CoreResponse{

    private List<Product> products;

    public FindByNameResponse(List<CoreError> errors, List<Product> products) {
        super(errors);
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean hasProducts(){
        return products != null && !products.isEmpty();
    }
}
