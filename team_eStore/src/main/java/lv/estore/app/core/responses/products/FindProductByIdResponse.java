package lv.estore.app.core.responses.products;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.responses.CoreResponse;

import java.util.List;

public class FindProductByIdResponse extends CoreResponse {
    private Product product;

    public FindProductByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindProductByIdResponse(final Product product) {
        this.product = product;
    }

    public boolean hasProduct(){
        return product != null;
    }

    public Product getProduct(){
        return product;
    }
}
