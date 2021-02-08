package internet_store.application.core.services.shopping_cart;

import internet_store.application.core.database.shopping_cart.ShoppingCartRepository;
import internet_store.application.core.domain.ShoppingCart;
import internet_store.application.core.requests.shopping_cart.FindShoppingCartByIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.shopping_cart.FindShoppingCartByIdResponse;
import internet_store.application.core.services.shopping_cart.validators.FindShoppingCartByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindShoppingCartByIdService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    FindShoppingCartByIdValidator validator;

    public FindShoppingCartByIdResponse execute(FindShoppingCartByIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new FindShoppingCartByIdResponse(errors);
        }

        ShoppingCart shoppingCart = shoppingCartRepository.findById(request.getId());
        return new FindShoppingCartByIdResponse(shoppingCart);
    }

}
