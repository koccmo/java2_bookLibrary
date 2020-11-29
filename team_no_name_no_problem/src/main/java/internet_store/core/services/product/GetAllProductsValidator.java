package internet_store.core.services.product;

import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class GetAllProductsValidator {

    public List<CoreError> validate (GetProductsRequest getProductsRequest){

        return new ArrayList<>();

    }
}
