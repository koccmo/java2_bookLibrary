package internet_store.core.services.product.validators;

import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.CoreError;
import internet_store.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class GetAllProductsValidator {

    public List<CoreError> validate (GetProductsRequest getProductsRequest){

        return new ArrayList<>();

    }
}
