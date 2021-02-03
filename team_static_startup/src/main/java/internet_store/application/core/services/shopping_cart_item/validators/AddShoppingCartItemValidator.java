package internet_store.application.core.services.shopping_cart_item.validators;

import internet_store.application.core.database.product.ProductRepository;
import internet_store.application.core.database.shopping_cart.ShoppingCartRepository;
import internet_store.application.core.requests.shopping_cart_item.AddShoppingCartItemRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddShoppingCartItemValidator {

    @Autowired private ShoppingCartRepository shoppingCartRepository;
    @Autowired private ProductRepository productRepository;

    public List<CoreError> validate(AddShoppingCartItemRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getQuantity() <= 0){
            errors.add(new CoreError("Quantity.", "Should be more than zero."));
        }

        try {
            shoppingCartRepository.findById(request.getShoppingCartId());
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            errors.add(new CoreError("Shopping Cart.", "Should EXIST or not be NULL."));
        }

        try{
            productRepository.findById(request.getProductId());
        }catch (Exception e){

        }finally {
            errors.add(new CoreError("Product.", "Should EXIST or not be NULL."));
        }

        return errors;
    }

}
