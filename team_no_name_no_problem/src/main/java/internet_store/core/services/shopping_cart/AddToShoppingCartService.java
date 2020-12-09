package internet_store.core.services.shopping_cart;

import internet_store.core.domain.ShoppingCart;
import internet_store.dependency_injection.DIComponent;
import internet_store.dependency_injection.DIDependency;

@DIComponent
public class AddToShoppingCartService {

    @DIDependency private ShoppingCart shoppingCart;

}
