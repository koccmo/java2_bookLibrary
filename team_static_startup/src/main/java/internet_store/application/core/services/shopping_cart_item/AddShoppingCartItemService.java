package internet_store.application.core.services.shopping_cart_item;

import internet_store.application.core.database.shopping_cart_item.ShoppingCartItemRepository;
import internet_store.application.core.requests.shopping_cart_item.AddShoppingCartItemRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.shopping_cart_item.AddShoppingCartItemResponse;
import internet_store.application.core.services.shopping_cart_item.validators.AddShoppingCartItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddShoppingCartItemService {

    @Autowired private ShoppingCartItemRepository itemRepository;
    @Autowired private AddShoppingCartItemValidator validator;

    public AddShoppingCartItemResponse execute(AddShoppingCartItemRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddShoppingCartItemResponse(errors);
        }

        Long cartItemId = itemRepository.add(
                request.getShoppingCartId(), request.getProductId(), request.getQuantity());

        return new AddShoppingCartItemResponse(cartItemId);
    }


}
