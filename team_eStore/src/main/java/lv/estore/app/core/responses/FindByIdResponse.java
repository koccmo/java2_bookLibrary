package lv.estore.app.core.responses;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;

import java.util.List;

public class FindByIdResponse extends CoreResponse{
    private Product product;

    public FindByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public FindByIdResponse(final Product product) {
        this.product = product;
    }

    public boolean hasProduct(){
        return product != null;
    }

    public Product getProduct(){
        return product;
    }
}
