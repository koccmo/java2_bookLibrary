package internet_store.application.core.services.shopping_cart;

import internet_store.application.core.database.customer.CustomerRepository;
import internet_store.application.core.database.shopping_cart.ShoppingCartRepository;
import internet_store.application.core.domain.Customer;
import internet_store.application.core.domain.ShoppingCart;
import internet_store.application.core.requests.shopping_cart.AddShoppingCartRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.shopping_cart.AddShoppingCartResponse;
import internet_store.application.core.services.shopping_cart.validators.AddShoppingCartValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddShoppingCartValidator validator;

    public AddShoppingCartResponse execute(AddShoppingCartRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddShoppingCartResponse(errors);
        }

        shoppingCartRepository.add(request.getCustomerId());
        Customer customer = customerRepository.findByCustomerId(request.getCustomerId()).get();
        ShoppingCart shoppingCart = new ShoppingCart(customer, true);
        return new AddShoppingCartResponse(shoppingCart);
    }

}
