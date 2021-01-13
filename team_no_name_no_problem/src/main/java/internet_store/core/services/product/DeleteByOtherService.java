/*package internet_store.core.services.product;

import internet_store.core.requests.product.DeleteProductByOtherRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.product.DeleteByOtherResponse;
import internet_store.core.response.product.DeleteProductResponse;
import internet_store.core.services.product.validators.DeleteByOtherRequestValidator;
import internet_store.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteByOtherService {

    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private DeleteByOtherRequestValidator deleteByOtherRequestValidator;

    public DeleteByOtherResponse execute(DeleteProductByOtherRequest deleteProductByOtherRequest) {

        List<CoreError> errors = deleteByOtherRequestValidator.validate(deleteProductByOtherRequest);

        if (!errors.isEmpty()) {
            return new DeleteByOtherResponse(errors);
        }


    }
*/