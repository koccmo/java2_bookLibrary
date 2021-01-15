package internet_store.core.response.product;

import internet_store.core.domain.Product;
import internet_store.core.response.CoreError;
import internet_store.core.response.CoreResponse;

import java.util.List;

public class DeleteByOtherResponse extends CoreResponse {

    List<Product> products;

    public DeleteByOtherResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteByOtherResponse(List<CoreError> errors, List<Product> products) {
        super(errors);
    }

    public List<Product> getProducts(){
        return products;
    }

}
