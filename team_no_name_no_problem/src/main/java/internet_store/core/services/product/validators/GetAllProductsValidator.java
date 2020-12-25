package internet_store.core.services.product.validators;

import internet_store.core.requests.product.GetProductsRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllProductsValidator {

    public List<CoreError> validate (GetProductsRequest getProductsRequest){

        return new ArrayList<>();

    }
}
