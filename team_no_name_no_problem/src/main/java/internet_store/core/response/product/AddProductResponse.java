package internet_store.core.response.product;

import internet_store.core.domain.Product;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class AddProductResponse extends CoreResponse {

    private Product product;

    public AddProductResponse(List<CoreError> errors){
        super(errors);
    }

    public AddProductResponse(Product product){
        this.product = product;
    }

    public Product getProduct(){
        return product;
    }

}
