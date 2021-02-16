package lv.estore.app.core.database.shopping_cart;

import lv.estore.app.core.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryShoppingCartRepository implements ShoppingCartRepository{

    private List<Product> productList = new ArrayList<>();

    @Override
    public void addProduct(final Product product) {
       productList.add(product);
    }


    @Override
    public void removeProduct(final Product product) {
        productList.remove(product);
    }

    @Override
    public List<Product> getProducts() {
        return productList;
    }
}
